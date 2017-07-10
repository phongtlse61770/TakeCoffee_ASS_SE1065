using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using Entity;
using Entity.Helper;

namespace Admin.Controllers
{
    public class OrderController : Controller
    {
        public ActionResult Index()
        {
            using (OrderHelper orderHelper = new OrderHelper())
            {
                ICollection<Order> orders = orderHelper.GetAllOrder();

                ViewBag.orders = orders;

                string username = "Asdas";
                string password = "Adsa";
                bool isAllow = false;

                UserHelper userHelper = new UserHelper();
                isAllow = userHelper.AuthenticateEmployee(username, password);
                


                userHelper.Dispose();
                return View();
            }
        }
    }
}