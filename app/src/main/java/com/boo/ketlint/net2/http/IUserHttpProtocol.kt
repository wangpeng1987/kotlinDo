package com.boo.ketlint.net2.http

import com.boo.ketlint.net2.domain.Category
import com.boo.ketlint.net2.domain.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IUserHttpProtocol {

    /**
     * 获取 今日推荐
     * */
    @GET("today")
    fun getToday(): Observable<Category>

    /**
     * 获取分页推荐
     * */
    @GET("data/all/30/{page}")
    fun getAllSeearch(@Path("page") page: Int): Observable<SearchList>


    /**
     * 通过用户名获取用户信息
     * @param userName 用户名
     * @return  用户基本信息
     * */
    @GET("/users/{userName}")
    fun getUserInfoByName(@Path("userName") userName: String): Observable<User>

    /**
     * @param userName 用户名
     * @param page 页码
     * @return Events列表数据
     * */
    @GET("/users/{userName}/events")
    fun getEventsByName(@Path("userName") userName: String, @Query("page") page: Int): Observable<MutableList<Event>>

    /**
     * @param userName 用户名
     * @param page 页码
     * @return Starred列表数据
     * */
    @GET("/users/{userName}/starred")
    fun getStarredByName(@Path("userName") userName: String, @Query("page") page: Int): Observable<MutableList<Starred>>

    /**
     * @param userName 用户名
     * @param page 页码
     * @returnt Followers列表数据
     * */
    @GET("/users/{userName}/followers")
    fun getFollowersByName(@Path("userName") userName: String, @Query("page") page: Int): Observable<MutableList<Follower>>

    /**
     * @param userName 用户名
     * @param page 页码
     * @returnt Repositories列表数据
     * */
    @GET("/users/{userName}/repos")
    fun getRepositoriesByName(@Path("userName") userName: String, @Query("page") page: Int): Observable<MutableList<Repository>>

    /**
     * @param userName 用户名
     * @param page 页码
     * @returnt Following列表数据
     * */
    @GET("/users/{userName}/following")
    fun getFollowingByName(@Path("userName") userName: String, @Query("page") page: Int): Observable<MutableList<Following>>
}