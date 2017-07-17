using Entity;
using Entity.Helper;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace Admin.Controllers
{
    public class UserController : Controller
    {
        // GET: User
        public ActionResult Index()
        {
            using (UserHelper userHelper = new UserHelper())
            {
                ICollection<User> users = userHelper.GetAllUser();
                ViewBag.users = users;
            }
            return View();
        }

        [HttpPost]
        public ActionResult Index(string submit, int? userId, string txtName, string txtPassword, string txtPhone, decimal? txtBalance, int? updateUserId, bool? chkAdmin)
        {
            switch (submit)
            {
                case "Add":
                    using (UserHelper userHelper = new UserHelper())
                    {
                        userHelper.CreateUser(txtName, txtPassword, txtPhone, txtBalance, chkAdmin);
                    }
                    return Index();
                case "Update":
                    using (UserHelper userHelper = new UserHelper())
                    {
                        userHelper.UpdateUser(updateUserId, txtPhone, chkAdmin, txtPassword);
                    }
                    return Index();
                case "Edit":
                    using (UserHelper userHelper = new UserHelper())
                    {
                        User user = userHelper.Find(userId);
                        ViewBag.user = user;
                    }
                    return Index();
                default:
                    throw new Exception();
            }
        }
    }
}