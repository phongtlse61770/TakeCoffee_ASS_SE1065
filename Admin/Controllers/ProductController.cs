using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using Entity.Helper;
using Entity;
using System.IO;

namespace Admin.Controllers
{
    public class ProductController : Controller
    {
        // GET: Product
        public ActionResult Index()
        {
            using (ProductHelper productHelper = new ProductHelper())
            {
                ICollection<Product> products = productHelper.GetAllProduct();
                //products.First(p => p.Category.ID == 1);
                ViewBag.products = products;

                //dynamic obj = new Object();
                //obj.a = "a";
                //obj.number = 123;
            }
            using (CategoryHelper categoryHelper = new CategoryHelper())
            {
                ICollection<Category> categories = categoryHelper.GetAllCategory();
                ViewBag.categories = categories;
            }
            return View();
        }

        [HttpPost]
        public ActionResult Index(int? productId, string submit, string txtName, int? slcCategory, string txtPrice, HttpPostedFileBase flImg, int? updateProductId,
            bool? chkRemoved)
        {            
            switch (submit)
            {
                case "Add":
                    string filename = saveImg(flImg);
                    using (ProductHelper productHelper = new ProductHelper())
                    {
                        productHelper.CreateProduct(txtName, slcCategory.Value, Convert.ToDecimal(txtPrice), filename);
                    }
                    return Index();
                case "Update":
                    string filename1 = saveImg(flImg);
                    using (ProductHelper productHelper = new ProductHelper())
                    {
                        productHelper.UpdateProduct(updateProductId, txtName, slcCategory.Value, Convert.ToDecimal(txtPrice), filename1, chkRemoved);
                    }
                    return Index();
                case "Edit":
                    using (ProductHelper productHelper = new ProductHelper())
                    {
                        Product product = productHelper.Find(productId);
                        ViewBag.product = product;
                    }   
                    return Index();
                default:
                    throw new Exception();
            }
        }

        public string saveImg (HttpPostedFileBase file)
        {
            if (file != null && file.ContentLength > 0)
            {
                // extract only the filename
                var fileName = Path.GetFileName(file.FileName);
                // store the file inside ~/App_Data/uploads folder
                if (fileName != null)
                {
                    var path = Path.Combine(Server.MapPath("~/App_Data/images"), fileName);
                    file.SaveAs(path);
                    return file.FileName; 
                }
            }
            throw new Exception();
        }
    }
}