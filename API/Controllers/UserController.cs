using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Http;
using Entity.Helper;
using Newtonsoft.Json.Linq;

namespace API.Controllers
{
    [RoutePrefix("user")]
    public class UserController : ApiController
    {
        [HttpPost]
        [Route("balance")]
        public IHttpActionResult GetBalance([FromBody] JObject obj)
        {
            int id;
            try
            {
                id = obj["id"].Value<int>();
            }
            catch (Exception)
            {
                return BadRequest("Invalid request");
            }
            //--------------------------
            using (UserHelper userHelper = new UserHelper())
            {
                Decimal balance = userHelper.GetBalance(id);
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
                bool isSuccess = userHelper.Authenticate(username, password);
                dynamic response = new JObject();
                response["result"] = isSuccess;
                return Ok(response);
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
                bool isSuccess = userHelper.CreateUser(username, password, phonenumber,false);
                dynamic response = new JObject();
                response["result"] = isSuccess;
                return Ok(response);
            }
        }
    }
}