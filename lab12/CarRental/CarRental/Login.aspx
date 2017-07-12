<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Login.aspx.cs" Inherits="CarRental.Login" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
    <div>
    
    </div>
        <asp:Label ID="Label1" runat="server" Text="Username:"></asp:Label>
        <asp:TextBox ID="username" runat="server"></asp:TextBox>
        <p>
            <asp:Label ID="Label2" runat="server" Text="Password:"></asp:Label>
            <asp:TextBox ID="password" runat="server"></asp:TextBox>
        </p>
        <asp:Button ID="loginButton" runat="server" OnClick="loginButton_Click" Text="Login" />
        <asp:Label ID="incorrenct" runat="server"></asp:Label>
    </form>
</body>
</html>
