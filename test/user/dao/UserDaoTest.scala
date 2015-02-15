package user.dao

import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import play.api.test._
import play.api.test.Helpers._
import user.dto.User
import user.dto.User

@RunWith(classOf[JUnitRunner])
class UserDaoTest extends Specification {

	"list" should {
		"get non zero list" in new WithApplication {
			val result = UserDao.list
			result.foreach { println }
			result.size must greaterThan(0)
		}
	}

	"add" should {
		"add 1 user" in new WithApplication {
			UserDao.insert(User(-1, "name", "pass", "N", "B", 0))
			UserDao.getByName("name").get.name must equalTo("name")
		}
	}

	"update" should {
		"update" in new WithApplication {
			val userOption = UserDao.getByName("name")
			var user = userOption.get
			val newUser = User(user.id, user.name, "paswd", user.blocked, user.active, user.version)
			UserDao.updateById(newUser.id, newUser)
			val updatedUsr = UserDao.selectById(user.id)
			updatedUsr.get.password must equalTo("paswd")
		}
	}

	"get" should {
		"get admin" in new WithApplication {
			val result = UserDao.getByName("admin")
			println(result)
			result.get.name must equalTo("admin")
		}
	}
  
  "clearing" should {
    "clear after test" in new WithApplication {
      val user = UserDao.getByName("name").get
      UserDao.deleteById(user.id)
    }
  }
}