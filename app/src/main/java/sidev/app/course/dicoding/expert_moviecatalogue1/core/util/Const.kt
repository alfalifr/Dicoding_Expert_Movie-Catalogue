package sidev.app.course.dicoding.expert_moviecatalogue1.core.util

import sidev.app.course.dicoding.expert_moviecatalogue1.BuildConfig
import sidev.lib.android.std.tool.util.`fun`.loge
import java.io.Serializable


object Const {
    enum class ShowType: Serializable {
        TV {
            override fun getDetailUrl(id: String, lang: String): String = getTvDetailUrl(id, lang)
            override fun getPopularUrl(lang: String, page: Int): String = getTvPopularUrl(lang, page)
        },
        MOVIE {
            override fun getDetailUrl(id: String, lang: String): String = getMovieDetailUrl(id, lang)
            override fun getPopularUrl(lang: String, page: Int): String = getMoviePopularUrl(lang, page)
        },
        ;

        abstract fun getDetailUrl(id: String, lang: String = "en-US"): String
        abstract fun getPopularUrl(lang: String = "en-US", page: Int = 1): String
    }

    const val ENDPOINT_ROOT = "https://api.themoviedb.org/3"
    private const val ENDPOINT_MOVIE = "$ENDPOINT_ROOT/movie"
    private const val ENDPOINT_TV = "$ENDPOINT_ROOT/tv"
    const val PATH_TV_POPULAR = "tv/popular"
    const val PATH_MOVIE_POPULAR = "movie/popular"
    private const val ENDPOINT_PUBLIC = "https://themoviedb.org"

    private const val ENDPOINT_IMG = "$ENDPOINT_PUBLIC/t/p"
    private const val ENDPOINT_IMG_300x450 = "$ENDPOINT_IMG/w300_and_h450_bestv2"
    private const val ENDPOINT_IMG_533x300 = "$ENDPOINT_IMG/w533_and_h300_bestv2"

    val API_KEY: String
        get()= BuildConfig.API_KEY.also { loge("Const.API_KEY = $it") }

    fun getImgUrl_300x450(fileName: String): String = "$ENDPOINT_IMG_300x450/$fileName"
    fun getImgUrl_533x300(fileName: String): String = "$ENDPOINT_IMG_533x300/$fileName"

    fun getMovieDetailUrl(id: String, lang: String = "en-US"): String =
        "$ENDPOINT_MOVIE/$id?api_key=$API_KEY&language=$lang"

    fun getMoviePopularUrl(lang: String = "en-US", page: Int = 1): String =
        "$ENDPOINT_MOVIE/popular?api_key=$API_KEY&language=$lang&$KEY_PAGE=$page"

    fun getTvDetailUrl(id: String, lang: String = "en-US"): String =
        "$ENDPOINT_TV/$id?api_key=$API_KEY&language=$lang"

    fun getTvPopularUrl(lang: String = "en-US", page: Int = 1): String =
        "$ENDPOINT_TV/popular?api_key=$API_KEY&language=$lang&$KEY_PAGE=$page"

    const val TIME_REFRESH = 1000L * 60 * 2

    const val MODULE_FAV = "favorite"
    const val PKG_FAV = "sidev.app.course.dicoding.expert_moviecatalogue1.favorite"
    const val PKG_FAV_ACT = "$PKG_FAV.ui.activity"
    const val ACT_FAV_DETAIL = "$PKG_FAV_ACT.DetailFavActivity"
    const val ACT_FAV_LIST = "$PKG_FAV_ACT.ShowFavListActivity"

    const val KEY_KEYWORD = "query"
    const val KEY_API_KEY = "api_key"
    const val KEY_PAGE = "page"

    const val KEY_TYPE = "type"
    const val KEY_SHOW = "show"
    const val KEY_ORIGINAL_TITLE = "original_title"
    const val KEY_ORIGINAL_NAME = "original_name"
    const val KEY_TOTAL_RESULTS = "total_results"
    const val KEY_TOTAL_PAGES = "total_pages"
    const val KEY_RELEASE = "release_date"
    const val KEY_FIRST_AIR_DATE = "first_air_date"
    const val KEY_IMG = "poster_path"
    const val KEY_RATING = "vote_average"
    const val KEY_BACKDROP = "backdrop_path"
    const val KEY_MOVIE_DURATION = "runtime"

    const val GET_SHOW_POPULAR_LIST = "getShowPopularList"
    const val GET_SHOW_DETAIL = "getShowDetail"
    const val GET_IS_FAV = "isFav"
    const val GET_FAV_LIST = "getFavList"
    const val INSERT_FAV = "insertFav"
    const val DELETE_FAV = "deleteFav"
    const val SEARCH_SHOW = "searchShow"
}