using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using Entity.Helper;

namespace Admin.Controllers
{
    public class LoginController : Controller
    {
        // GET: Login
        public ActionResult Index()
        {
            return View();
        }

        [HttpPost]
        public ActionResult Index(string username,string password)
        {
            bool isAllow = false;
            UserHelper userHelper = new UserHelper();
            isAllow = userHelper.Authenticate(username, password);
            //userHelper.IsAdmin()
            userHelper.Dispose();
            if(isAllow)
            {
                Session["username"] = username;
                return RedirectToAction("Index", "Home");
            }
            ViewBag.isSuccess = false;
            return View();            
        }
    }
}