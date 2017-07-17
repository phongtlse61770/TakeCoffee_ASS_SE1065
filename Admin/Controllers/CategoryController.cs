using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using Entity;
using Entity.Helper;

namespace Admin.Controllers
{
    public class CategoryController : Controller
    {
        // GET: Category
        public ActionResult Index()
        {
            using (CategoryHelper categoryHelper = new CategoryHelper())
            {
                ICollection<Category> categories = categoryHelper.GetAllCategory();
                ViewBag.categories = categories;
            }
            return View();
        }

        [HttpPost]
        public ActionResult Index(string submit, int? categoryId, string txtName, int? updateCategoryId)
        {
            switch (submit)
            {
                case "Add":
                    using (CategoryHelper categoryHelper = new CategoryHelper())
                    {
                        categoryHelper.InsertCategory(txtName);
                    }
                    return Index();
                case "Update":
                    using (CategoryHelper categoryHelper = new CategoryHelper())
                    {
                        categoryHelper.UpdateCategory(updateCategoryId, txtName);
                    }
                    return Index();
                case "Edit":
                    using (CategoryHelper categoryHelper = new CategoryHelper())
                    {
                        Category category= categoryHelper.Find(categoryId);
                        ViewBag.category = category;
                    }
                    return Index();
                default:
                    throw new Exception();
            }
        }
    }
}