<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Browse.aspx.cs" Inherits="CarRental.Browse" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
    <style type="text/css">
        #div2 {
            height: 102px;
        }
    </style>
</head>
<body>
    <form id="form1" runat="server">
        
         <asp:DropDownList ID="DropDownList1" runat="server" OnSelectedIndexChanged="DropDownList1_SelectedIndexChanged"  AutoPostBack="true"></asp:DropDownList>
            <asp:Label ID="Label1" runat="server" Text="Label"></asp:Label>
        
  

    

    </form>
            <div id="div2" runat="server">
             </div>
        
  

    

    </body>
</html>
