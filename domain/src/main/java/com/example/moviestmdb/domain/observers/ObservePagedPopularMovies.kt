package com.example.moviestmdb.domain.observers

import androidx.paging.*
import com.example.moviestmdb.Movie
import com.example.moviestmdb.core.data.movies.MoviesStore
import com.example.moviestmdb.core.di.PopularMovies
import com.example.moviestmdb.domain.MoviesPagingSource
import com.example.moviestmdb.domain.PaginatedMovieRemoteMediator
import com.example.moviestmdb.domain.PagingInteractor
import com.example.moviestmdb.domain.interactors.UpdatePopularMovies
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class ObservePagedPopularMovies @Inject constructor(
    @PopularMovies private val popularMoviesStore: MoviesStore,
    private val updatePopularMovies: UpdatePopularMovies,
) : PagingInteractor<ObservePagedPopularMovies.Params, Movie>() {
    override fun createObservable(
        params: Params
    ): Flow<PagingData<Movie>> {
        return Pager(
            config = params.pagingConfig,
            remoteMediator = PaginatedMovieRemoteMediator(moviesStore = popularMoviesStore) { page ->
                updatePopularMovies.executeSync(UpdatePopularMovies.Params(page))
                pagingSourceFactory.invalidate()
            },
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    private val pagingSourceFactory = InvalidatingPagingSourceFactory(::createPagingSource)

    private fun createPagingSource(): MoviesPagingSource {
        return MoviesPagingSource(popularMoviesStore)
    }

    data class Params(
        override val pagingConfig: PagingConfig,
    ) : Parameters<Movie>
}