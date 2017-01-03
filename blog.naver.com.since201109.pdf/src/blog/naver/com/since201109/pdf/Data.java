package blog.naver.com.since201109.pdf;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Data {
   private String classname ;
   private int semester;
   private String filename;
   private int listindex;
   Data(String file, int index)
   {
      classname = "";
      semester = 0;
      filename = file;
      listindex = index;
      
   }
   void setfile(String file)
   {
      filename = file;
   }
   void setlistindex(int index )
   {
      listindex = index;
   }
   String getfile()
   {
      return filename;
   }
   int getlistindex()
   {
      return listindex;
   }

}