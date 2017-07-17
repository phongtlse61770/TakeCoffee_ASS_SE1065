using System;
using System.Collections.Specialized;
using System.IO;
using System.Net;
using System.Web;
using System.Web.Configuration;
using System.Web.Http;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;

namespace API.Controllers
{
    [RoutePrefix("utility")]
    public class UtilityController : ApiController
    {
        [HttpPost]
        [Route("shipfee")]
        public IHttpActionResult GetShipFee([FromBody]JObject jObject)
        {
            String clientLongitude;
            String clientLatitude;
            try
            {
                clientLongitude = jObject["Longitude"].Value<string>();
                clientLatitude = jObject["Latitude"].Value<string>();
            }
            catch (Exception)
            {
                return BadRequest("Invalid request body");
            }
            //-------------------------------------
            String storeLongitude = WebConfigurationManager.AppSettings["StoreLongitude"]; 
            String storeLatitude = WebConfigurationManager.AppSettings["StoreLatitude"];
            
            NameValueCollection queryStringHelper = HttpUtility.ParseQueryString(string.Empty);

            queryStringHelper["key"] = "AIzaSyC306FkxzrlSP_aJU807hC-niS0hNqZpHw";
            queryStringHelper["units"] = "metric";
            queryStringHelper["origins"] = $"{storeLatitude},{storeLongitude}";
            queryStringHelper["destinations"] = $"{clientLatitude},{clientLongitude}";
            
            var request = WebRequest.Create("https://maps.googleapis.com/maps/api/distancematrix/json?"+queryStringHelper);
            request.Method = "GET";
            var response = (HttpWebResponse)request.GetResponse();
            var responseString = new StreamReader(response.GetResponseStream()).ReadToEnd();

            JObject distanceResponse = JObject.Parse(responseString);

            string distance = distanceResponse["rows"].Value<JArray>()[0]["elements"].Value<JArray>()[0]["distance"].Value<JObject>()["value"].Value<string>();

            JObject responseJson = new JObject
            {
                ["distance"] = distance,
                ["fee"] = (Double.Parse(distance) * 5) / 1000
            };

            return Ok(responseJson);
        }
    }
}