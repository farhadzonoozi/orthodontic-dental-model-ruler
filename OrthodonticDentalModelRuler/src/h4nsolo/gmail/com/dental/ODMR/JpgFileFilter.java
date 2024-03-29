package h4nsolo.gmail.com.dental.ODMR;
import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 * Creates a file filter that accepts only *.jpg files.
 *
 * @author Roedy Green
 * @version 1.0
 * @since 2004-05-06
 */

public class JpgFileFilter extends javax.swing.filechooser.FileFilter
   {

   /**
    * Creates a file filter that accepts only *.jpg files.
    *
    */
   public JpgFileFilter()
      {
      }

   /**
    * Return true if this file should be shown in the directory pane,
    * false if it shouldn't.
    *
    * accepts directories and.jpg files.
    *
    * @see FileFilter#accepts
    */
   public boolean accept( File f )
      {
      if ( f != null )
         {
         if ( f.isDirectory() )
            {
            return true;
            }
         String name = f.getName().toLowerCase();
         return name.endsWith( ".jpg" );
         }
      else
         {
         return false;
         }
      }

   /**
   * Returns the human readable description of this filter.
   * @see setDescription
   * @see setExtensionListInDescription
   * @see isExtensionListInDescription
   * @see FileFilter#getDescription
   */
   public String getDescription()
      {
      return "*.jpg image files";
      }
   }