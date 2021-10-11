import java.sql.*;
class crud
{
 public static void main(String[] args) throws Exception
 {
  String title;
  int code;
  try
  {
   Class.forName("com.mysql.cj.jdbc.Driver");
   Connection c= DriverManager.getConnection("jdbc:mysql://localhost:3306/crud", "akshat","");
   Statement s= c.createStatement();

   String statement;
   
   while(true)
   {
    System.out.println("Select an option:");
    System.out.println("1.add");
    System.out.println("2.delete");
    System.out.println("3.update");
    System.out.println("4.display");
    System.out.println("5.exit");
    int x=0;
    String str;
    x=Keyboard.getInt();

    if(x==1)
    {
     title=Keyboard.getString("Enter the title to be add:");
     s.executeUpdate("INSERT INTO designations (title) VALUES ('"+title+"')",Statement.RETURN_GENERATED_KEYS);
     ResultSet r=s.getGeneratedKeys();
     r.next();
     System.out.println(title+" inserted with code:"+r.getInt(1));
    }
    else if(x==2)
    {
     title=Keyboard.getString("Enter the title to be deleted:");
     s.executeUpdate("DELETE FROM designations WHERE title='"+title+"'");
     System.out.println(title+" deleted");
    }
    else if(x==3)
    {
     String newTitle;
     title=Keyboard.getString("Enter the title to be updated:");
     newTitle=Keyboard.getString("Enter the new title:");
     s.executeUpdate("UPDATE designations SET title='"+newTitle+"' WHERE title='"+title+"'");
     System.out.println(title+" updated to "+newTitle);
    }
    else if(x==4)
    {
     ResultSet r;
     r=s.executeQuery("SELECT * FROM designations");
     while(r.next())
     {
      code=r.getInt("code");
      title=r.getString("title").trim();
      System.out.println("Code: "+code+" Title: "+title);
     }
     r.close();
    }
    else if(x==5)
    {
     break;
    }
    else
    {
     System.out.println("Enter valid response.");
    }
   }
   s.close();
   c.close();
  }
  catch(Exception e)
  {
   System.out.println("AN ERROR OCCURED");
   e.printStackTrace();
  } 
 }
}