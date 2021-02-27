package app.student.movieapp.presenter


import android.os.Handler
import android.view.View
import android.widget.AbsListView
import androidx.recyclerview.widget.RecyclerView
import app.student.movieapp.R
import app.student.movieapp.adapter.MovieAdapter
import app.student.movieapp.contract.MovieListContract
import app.student.movieapp.core.MovieListResourceManager
import app.student.movieapp.core.model.NetworkError
import app.student.movieapp.core.model.NetworkFailure
import app.student.movieapp.core.model.NetworkSuccess
import app.student.movieapp.model.Movie
import app.student.movieapp.monitoring.MovieListMonitoring
import app.student.movieapp.repository.MovieListRepositoryImpl
import kotlinx.android.synthetic.main.fragment_movie_list.view.*
import kotlin.reflect.KClass

class MovieListPresenter(private val view: MovieListContract.View, private val repository: MovieListRepositoryImpl,
private val monitoring: MovieListMonitoring) : MovieListContract.Presenter,
MovieListContract.MovieListListerner, MovieListContract.onScrolledAdapterListener {

    private var monitor: MovieListContract.Monitor? = null
    private var resourceManager: MovieListContract.ResourceManager? = null
    init {
        repository.attachListener(this)
        monitor = monitoring
    }
    fun attachResourceManager(resource: MovieListResourceManager){
        resourceManager = resource
    }

    override fun onGetListMoviesTop(page: Int) {
        repository.getListMovies(page)
    }

    override fun onGetListMoviesPopular(page: Int) {
        repository.getListMoviesPopular(page)
    }

    override fun onPrepareVisualizationMoviesTop(page: Int) {
        view.startSkeletonMoviesTop()
        onGetListMoviesTop(page)
    }

    override fun onPrepareVisualizationMoviesPopular(page: Int) {
        view.startSkeletonMoviesPopular()
        onGetListMoviesPopular(page)
    }


    override fun onDisposable() {
        repository.disposableInstance()
    }
    override fun <T : Any> onResponseOnErrorNetwork(clazz: KClass<T>) {
        when(clazz) {
            Throwable::class -> {clazz.members
                    .forEach { action -> println(action.name) }}

            NetworkFailure::class -> {
                clazz.members
                        .forEach { action -> println(action.name) }
            }
        }
    }

    override fun <T> onResponseOnErrorNetwork(t: T) {
        when(t){
            is NetworkFailure -> { view.onMessageError(t as NetworkFailure)
                            monitor?.trackNetworkFailure(t as NetworkFailure)
            }

            is Throwable -> {view.onMessageError(t as Throwable)
                            monitor?.trackNetworkError(NetworkError(t as Throwable))}
        }
    }

    override fun listMoviesSearchPopular(moviesList: List<Movie>) {
        Handler().postDelayed({
            view.onShowListMoviesPopular(moviesList)
            monitor?.trackNetworkSuccess(NetworkSuccess(resourceManager?.getStringNetworkSuccessManager()!!,
                resourceManager?.getStringNetworkSuccessOperationMovieListPopular()!!))
        }, TIME_SKELETON)

    }

    override fun listMoviesSearchTop(moviesList: List<Movie>) {
        Handler().postDelayed({
            view.onShowListMoviesTop(moviesList)
            monitor?.trackNetworkSuccess(NetworkSuccess(resourceManager?.getStringNetworkSuccessManager()!!,
                resourceManager?.getStringNetworkSuccessOperationMovieListTop()!!))
        },TIME_SKELETON)
    }


    override fun onErrorSearch(t: Throwable) {
        onResponseOnErrorNetwork(t)
    }

    override fun onErrorSearch(f: NetworkFailure) {
        onResponseOnErrorNetwork(f)

    }
    companion object {
        private const val TIME_SKELETON: Long = 3000
        private var PAGE_INT_SCROLED_POPULAR=1
        private var PAGE_INT_SCROLED_TOP=1
        private const val ID_RECYCLER_VIEW_POPULAR = R.id.recyclerViewPopular
        private const val ID_RECYCLER_VIEW_TOP = R.id.recyclerViewTop


    }

    override fun onScrolledDown(flag: Boolean,recyclerView: RecyclerView) {
        if(flag){
            if(recyclerView.id == ID_RECYCLER_VIEW_POPULAR){
                PAGE_INT_SCROLED_POPULAR+=1
                onPrepareVisualizationMoviesPopular(PAGE_INT_SCROLED_POPULAR)
                recyclerView.adapter = null
                view.startSkeletonMoviesPopular()
            }
            if(recyclerView.id == ID_RECYCLER_VIEW_TOP){
                PAGE_INT_SCROLED_TOP+=1
                onPrepareVisualizationMoviesTop(PAGE_INT_SCROLED_TOP)
                recyclerView.adapter = null
                view.startSkeletonMoviesTop()
            }
        }
    }




}
