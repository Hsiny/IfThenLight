using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace IfThenLight.Controllers
{
    public class UserController : Controller
    {
        IfThenLightEntities db = new IfThenLightEntities();
        // GET: User
        /// <summary>
        /// 插入消息
        /// </summary>
        /// <param name="name">用户名</param>
        /// <param name="password">密码</param>
        /// <returns>json result:1或0</returns>
        public ActionResult Insert(string name,string password) {
            JsonResult ret = new JsonResult();
            try {
                db.User_t.Add(new User_t()
                {User_Update=DateTime.Now,
                    User_Name = name,
                    User_Password = password
                });
                db.SaveChanges();
                return Json(new { result = "1" }, JsonRequestBehavior.AllowGet);
            } catch {
                return Json(new { result = "0" }, JsonRequestBehavior.AllowGet);
            }
           
          
        }
    }
}