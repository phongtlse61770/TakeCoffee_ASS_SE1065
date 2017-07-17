using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace Admin.Controllers
{
    public class HomeController : Controller
    {
        public ActionResult Index()
        {
            if (string.IsNullOrEmpty(Session["username"] as string))
            {
                return new RedirectResult("login");
            }
            return View();
        }
    }
}