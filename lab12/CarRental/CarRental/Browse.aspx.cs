using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace CarRental
{
    public partial class Browse : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            MySql.Data.MySqlClient.MySqlConnection conn;
            string myConnectionString;
            myConnectionString = "server=localhost;uid=root;pwd=root;database=cars;";
            try
            {
                conn = new MySql.Data.MySqlClient.MySqlConnection();
                conn.ConnectionString = myConnectionString;
                conn.Open();

                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "select * from car";
                MySqlDataReader myreader = cmd.ExecuteReader();

                div2.InnerHtml = "<table border='1'>" +
                                    "<tr><th> Id </th>" +
                                    "<th> Model </th>" + 
                                    "<th> Price </th>" + 
                                    "<th> Color </th>" +
                                    "<th> Age </th>" + 
//                                    "< th > Edit </ th >" + 
//                                    "< th > Delete </ th >" + 
                                    "</ tr >";
                int i = 0;
                while (myreader.Read())
                {
                    div2.InnerHtml += "<tr>" + "<td>" + myreader.GetInt32(0) + "</td>" + "<td>" + myreader.GetString(1) + "</td>" +
                        "<td>" + myreader.GetInt32(2) + "</td>" + "<td>" + myreader.GetString(3) + "</td>" + "<td>" +
                        myreader.GetInt32(4) + "</td>" +"</tr>";
                    if (!DropDownList1.Items.Contains(new
                            ListItem(myreader.GetString(1))))
                    {
                        DropDownList1.Items.Insert(i, new ListItem(myreader.GetString(1), myreader.GetString(1)));
                        i++;
                    }
                }
                div2.InnerHtml += "</table>";
                myreader.Close();
                
                conn.Close();
            }
            catch (MySql.Data.MySqlClient.MySqlException ex)
            {

                Response.Write(ex.Message);
            }
        }

        protected void DropDownList1_SelectedIndexChanged(object sender, EventArgs e)
        {
            this.Label1.Text = DropDownList1.SelectedValue;
            MySql.Data.MySqlClient.MySqlConnection conn;
            string myConnectionString;
            myConnectionString = "server=localhost;uid=root;pwd=root;database=cars;";
            try
            {
                conn = new MySql.Data.MySqlClient.MySqlConnection();
                conn.ConnectionString = myConnectionString;
                conn.Open();

                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "select * from car where model=@brand";
                cmd.Prepare();
                cmd.Parameters.AddWithValue("@brand" ,DropDownList1.SelectedValue);
                MySqlDataReader myreader = cmd.ExecuteReader();
                ClientScript.RegisterStartupScript(this.GetType(), "remove", "<script>$('#div2').remove();</script>");


                div2.InnerHtml = "<table border='1'>" +
                                    "<tr><th> Id </th>" +
                                    "<th> Model </th>" +
                                    "<th> Price </th>" +
                                    "<th> Color </th>" +
                                    "<th> Age </th>" +
                                    //                                    "< th > Edit </ th >" + 
                                    //                                    "< th > Delete </ th >" + 
                                    "</ tr >";
                int i = 0;
                while (myreader.Read())
                {
                    div2.InnerHtml += "<tr>" + "<td>" + myreader.GetInt32(0) + "</td>" + "<td>" + myreader.GetString(1) + "</td>" +
                        "<td>" + myreader.GetInt32(2) + "</td>" + "<td>" + myreader.GetString(3) + "</td>" + "<td>" +
                        myreader.GetInt32(4) + "</td>" + "</tr>";
                }
                div2.InnerHtml += "</table>";
                myreader.Close();

                conn.Close();
            }
            catch (MySql.Data.MySqlClient.MySqlException ex)
            {

                Response.Write(ex.Message);
            }
        }
    }
}