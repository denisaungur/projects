using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using MySql.Data.MySqlClient;


namespace CarRental
{
    public partial class Login : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void loginButton_Click(object sender, EventArgs e)
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
                cmd.CommandText = "select * from users";
                MySqlDataReader myreader = cmd.ExecuteReader();
                bool found = false;

                while (myreader.Read())
                {
                    if (myreader.GetString(0).Equals(this.username.Text) && myreader.GetString(1).Equals(this.password.Text))
                    {
                        found = true;
                    } 
                }
                myreader.Close();
                if ( found.Equals(true))
                {
                    this.incorrenct.Text = "Conncected";
                }else
                {
                    this.incorrenct.Text = "Wrong username or password";
                }
                conn.Close();
                Response.Redirect("Browse.aspx");
            }
            catch (MySql.Data.MySqlClient.MySqlException ex)
            {

                Response.Write(ex.Message);
            }

        }
    }
}