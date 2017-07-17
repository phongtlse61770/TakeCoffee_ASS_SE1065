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

                if(String.IsNullOrEmpty(Session["username"] as string))
                {
                    return new RedirectResult("login");
                }
                
                return View();
            }
        }
    }
}