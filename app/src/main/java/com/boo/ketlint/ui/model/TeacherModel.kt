package  com.boo.ketlint.ui.model

import com.boo.ketlint.LOGS
import com.boo.ketlint.sql.base.AppDataBase
import com.boo.ketlint.sql.tea.Teacher
import com.boo.ketlint.sql.tea.TeacherDao
import com.boo.ketlint.ui.contract.TeacherContract
import io.reactivex.Observable
import mvp.ljb.kt.model.BaseModel

/**
 * @Author Kotlin MVP Plugin
 * @Date 2019/08/22
 * @Description input description
 **/
class TeacherModel : BaseModel(), TeacherContract.IModel {
    override fun getTeacherCount(): Observable<Int> {
        //获取数据
        val tDao: TeacherDao = AppDataBase.instance.getTeacherDao()
        val sList_select_1: MutableList<Teacher> = tDao.getAllTeachers()
        LOGS.e("TeacherDao size : " + sList_select_1.size)
        return Observable.just(sList_select_1.size)
    }
}