package SystemCVBuilder;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

    public class ExportToPDF {
        public static void main(String[] args) {
            try {
                // Kết nối đến cơ sở dữ liệu
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cvdatasystem", "root", "123456");

                // Tạo một tài liệu PDF mới
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream("output.pdf"));
                document.open();

                // Truy vấn dữ liệu từ cơ sở dữ liệu
                String sql = "SELECT * FROM cv_info";
                PreparedStatement statement = conn.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();

                // Thêm thông tin từ cơ sở dữ liệu vào tài liệu PDF
                while (resultSet.next()) {
                    document.add(new Paragraph("First Name: " + resultSet.getString("first_name")));
                    document.add(new Paragraph("Last Name: " + resultSet.getString("last_name")));
                    // Thêm thông tin khác tương tự

                    // Thêm một dòng trống giữa mỗi bản ghi
                    document.add(new Paragraph("\n"));
                }

                // Đóng kết nối và tài liệu PDF
                conn.close();
                document.close();

                System.out.println("PDF file exported successfully.");

            } catch (SQLException | DocumentException | FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


