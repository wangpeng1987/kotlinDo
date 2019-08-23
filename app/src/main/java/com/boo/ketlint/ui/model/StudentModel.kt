package  com.boo.ketlint.ui.model

import com.boo.ketlint.LOGS
import com.boo.ketlint.sql.base.AppDataBase
import com.boo.ketlint.sql.stu.Student
import com.boo.ketlint.sql.stu.StudentDao
import com.boo.ketlint.ui.contract.StudentContract
import com.ljb.mvp.kotlin.domain.Follower
import com.ljb.mvp.kotlin.domain.User
import com.ljb.mvp.kotlin.protocol.http.IUserHttpProtocol
import io.reactivex.Observable
import mvp.ljb.kt.model.BaseModel
import net.ljb.kt.client.HttpFactory

/**
 * @Author wop
 * @Date 2019/08/22
 * @Description input description
 **/
class StudentModel : BaseModel(), StudentContract.IModel {
    override fun getId(id: Int): Observable<Student> {
        //获取数据
        val sDao: StudentDao = AppDataBase.instance.getStudentDao()
        val sList_select_1: Student = sDao.getStudnet(id)
        LOGS.e("StudentPresenter : " + sList_select_1)
        return Observable.just(sList_select_1);
    }

//    override fun getFollowers(page: Int): Observable<MutableList<Follower>> {
//        return HttpFactory.getProtocol(IUserHttpProtocol::class.java)
//            .getFollowersByName(LoginUser.login, page)
//    }

}