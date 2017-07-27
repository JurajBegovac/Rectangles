package begovac.rectangles.repository.realm

import io.realm.RealmList
import io.realm.RealmObject

/** Created by juraj begovac on 27/07/2017. */

open class RealmRectangle(var ordinalNumber: Int = 0,
                          var color: Int = 0,
                          var indexInList: Int = 0) : RealmObject()

open class RealmData(var rows: Int = 0,
                     var columns: Int = 0,
                     var rectangles: RealmList<RealmRectangle> = RealmList()) : RealmObject()
