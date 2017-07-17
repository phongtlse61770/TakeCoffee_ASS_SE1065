using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Http;
using Entity;
using Entity.Helper;
using Entity.JsonModel;
using Newtonsoft.Json.Linq;

namespace API.Controllers
{
    [RoutePrefix("user")]
    public class UserController : ApiController
    {
        [HttpPost]
        [Route("balance")]
        public IHttpActionResult GetBalance()
        {
            string username = Request.Headers.GetValues("username").First();
            string password = Request.Headers.GetValues("password").First();

            using (UserHelper userHelper = new UserHelper())
            {
                Decimal balance = userHelper.GetBalance(username,password);
                dynamic response = new JObject();
                response["balance"] = balance;
                return Ok(response);
            }
        }

        [HttpPost]
        [Route("checklogin")]
        public IHttpActionResult CheckLogin([FromBody] JObject jsonObject)
        {
            string username;
            string password;
            try
            {
                username = jsonObject["username"].Value<string>();
                password = jsonObject["password"].Value<string>();
            }
            catch (Exception)
            {
                return BadRequest("Invalid request");
            }
            //--------------------------
            using (UserHelper userHelper = new UserHelper())
            {
                User user = userHelper.GetUser(username, password);
                bool isSuccess = user != null;
                bool isEmployee = false;
                if (isSuccess)
                {
                    if (user.isEmployee != null) isEmployee = user.isEmployee.Value;
                }

                dynamic response = new JObject();
                response["result"] = isSuccess;
                response["isEmployee"] = isEmployee;
                return Ok(response);
            }
        }
        
        [HttpPost]
        [Route("checkloginEmployee")]
        public IHttpActionResult CheckLoginEmployee([FromBody] JObject jsonObject)
        {
            string username;
            string password;
            try
            {
                username = jsonObject["username"].Value<string>();
                password = jsonObject["password"].Value<string>();
            }
            catch (Exception)
            {
                return BadRequest("Invalid request");
            }
            //--------------------------
            using (UserHelper userHelper = new UserHelper())
            {
                bool isSuccess = userHelper.AuthenticateEmployee(username, password);
                dynamic response = new JObject();
                response["result"] = isSuccess;
                return Ok(response);
            }
        }
        
        [HttpPost]
        [Route("getProfile")]
        public IHttpActionResult GetProfile()
        {
            string username = Request.Headers.GetValues("username").First();
            string password = Request.Headers.GetValues("username").First();
            
            using (UserHelper userHelper = new UserHelper())
            {
                User user = userHelper.GetUser(username,password);
                
                return Ok((UserJsonModel)user);
            }
        }

        [HttpPost]
        [Route("create")]
        public IHttpActionResult Register([FromBody] JObject jsonObject)
        {
            string username;
            string password;
            string phonenumber;

            try
            {
                username = jsonObject["username"].Value<string>();
                password = jsonObject["password"].Value<string>();
                phonenumber = jsonObject["phonenumber"].Value<string>();
            }
            catch (Exception)
            {
                return BadRequest("Invalid request");
            }
            //---------------------------------------------
            using (UserHelper userHelper = new UserHelper())
            {
                User user = userHelper.CreateUser(username, password, phonenumber,0,false);
                dynamic response = new JObject();
                response["result"] = user != null;
                return Ok(response);
            }
        }
    }
}