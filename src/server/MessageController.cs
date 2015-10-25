using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Web;
using System.Web.Mvc;

namespace IfThenLight.Controllers
{
    public class MessageController : Controller
    {
        IfThenLightEntities db = new IfThenLightEntities();
        // GET: Message
        /// <summary>
        /// 插入消息
        /// </summary>
        /// <param name="user">用户姓名</param>
        /// <param name="tag">消息类型</param>
        /// <param name="title">消息标题</param>
        /// <param name="content">消息内容</param>
        /// <returns>json result:1或0</returns>
        public string Insert(string user, string tag, string title, string content)
        {
           
            try
            {
                db.Message_t.Add(
               new Message_t()
               {
                   Message_Tag = tag,
                   Message_Title = title,
                   Message_Content = content,
                   Message_CreateDt = DateTime.Now,
                   Message_UpdateDt = DateTime.Now,
                   Message_IsRead = 0,
                   Message_UserId = db.User_t.Where(p=>p.User_Name==user).FirstOrDefault().User_Id,
               }

               );
                db.SaveChanges();
                return "1";

            }
            catch
            {
                return "0";
            }


        }
        /// <summary>
        /// 获取某用户的消息列表
        /// </summary>
        /// <param name="userid">用户id</param>
        /// <returns>消息列表  Message_Tag Message_Title  Message_Content  Message_CreateDt  Message_UpdateDt  Message_IsRead   Message_UserId</returns>
        public string GetList(int userid)
        {
            //JsonResult ret = new JsonResult();
     
            var list = db.Message_t.Where(p => p.Message_UserId == userid).ToList();

         
            string json = "";
        
            foreach (var item in list)
            {
                json += item.Message_Id;
                json += "|*|" + item.Message_Title;
                json += "|*|" +  item.Message_Content;
                json += "|*|" + item.Message_CreateDt.ToShortTimeString();
                json += "|*|" + item.Message_Tag;
                json += "||";
            }
            json = json.Substring(0,json.Length-2);
            return json;
        }
        /// <summary>
        /// 获取某用户的未读消息
        /// </summary>
        /// <param name="userid">用户id</param>
        /// <returns>消息列表 和上面的一样</returns>
        public string GetUnRead(int userid)
        {
            var list = db.Message_t.Where(p => p.Message_UserId == userid & p.Message_IsRead == 0).ToList();
            string json = "";
            if (list.Count != 0)
            {
                foreach (var item in list)
                {
                    json += item.Message_Id;
                    json += "|*|" + item.Message_Title;
                    json += "|*|" + item.Message_Content;
                    json += "|*|" + item.Message_CreateDt.ToShortTimeString();
                    json += "|*|" + item.Message_Tag;
                    json += "||";
                }
                json = json.Substring(0, json.Length - 2);
            }
            else {
                json = "0";
            }
           
            return json;
        }
        /// <summary>
        /// 设置消息已读
        /// </summary>
        /// <param name="userid">用户id</param>
        /// <returns>返回 json result:1或0</returns>
        public string SetRead(int userid)
        {
            var change = db.User_t.Where(p => p.User_Id == userid).FirstOrDefault().User_Update=db.Message_t.Where(p=>p.Message_UserId==userid).OrderByDescending(p=>p.Message_CreateDt).FirstOrDefault().Message_CreateDt;
            try
            {
               var list= db.Message_t.Where(p => p.Message_UserId == userid&&p.Message_IsRead==0).ToList();
                foreach (var item in list) {
                    item.Message_IsRead = 1;
}
                db.SaveChanges();
                return "1";
            }
            catch
            {
                return "0";
            }

        }
        /// <summary>
        /// 获取是否有更新
        /// </summary>
        /// <param name="userid">用户id</param>
        /// <returns>1成功 0失败</returns>
        public string GetUpdate(int userid)
        {

            var lastmessage=  db.Message_t.Where(p => p.Message_UserId == userid).OrderByDescending(p => p.Message_CreateDt).FirstOrDefault().Message_CreateDt;
            var update = db.User_t.Where(p => p.User_Id == userid).FirstOrDefault().User_Update;
            if (lastmessage > update) {
                return "1";
            }
            return "0";
        }
    }
}