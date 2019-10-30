package des;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.util.Scanner;
import java.security.spec.KeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

class DesEncrypter {
  Cipher ecipher; //
  Cipher dcipher; // VARIABLE GLOBAL
  SecretKey mykey;//
  
  //PEMROSESAN KUNCI YANG AKAN DIGUNAKAN=====================
  DesEncrypter(String kunci) throws Exception {
    ecipher = Cipher.getInstance("DES");
    dcipher = Cipher.getInstance("DES");
    
    SecretKeyFactory keys = SecretKeyFactory.getInstance("DES");
    KeySpec keyspec = new DESKeySpec(kunci.getBytes("UTF8"));
    mykey = keys.generateSecret(keyspec);
    
    ecipher.init(Cipher.ENCRYPT_MODE, mykey);
    dcipher.init(Cipher.DECRYPT_MODE, mykey);
  }

  //FUNGSI ENKRIPSI==========================================
  public String encrypt(String str) throws Exception {
    // Encode String kedalam bytes menggunakan utf-8
    byte[] utf8 = str.getBytes("UTF8");
    // Enkripsi
    byte[] enc = ecipher.doFinal(utf8);
    // Encode bytes ke base64 untuk mendapatkan String
    return new sun.misc.BASE64Encoder().encode(enc);
  }

  //FUNGSI DEKRIPSI==========================================
  public String decrypt(String str) throws Exception {      
    // Decode base64 untuk mendapatkan bytes
    byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);
    // Dekripsi
    byte[] utf8 = dcipher.doFinal(dec);
    // Decode menggunakan utf-8
    return new String(utf8, "UTF8");
  }
}

public class DES {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Plain text :");
        String input = sc.nextLine();
        String keys = "adfe45tg";
               
        DesEncrypter encrypter = new DesEncrypter(keys);
        
        System.out.println("key : " + keys +"\n"); //mencetak nilai kunci
        
        //PROSES ENKRIPSI
        long mulaiEn = System.currentTimeMillis(); //ambil waktu mulai dari enkripsi
        String encrypted = encrypter.encrypt(input); //proses enkripsi dilakukan
        System.out.print("Hasil Enkripsi :");
        System.out.println(encrypter.encrypt(input)); //cetak hasil enkripsi
        long selesaiEn = System.currentTimeMillis(); //menghitung waktu estimasi enkripsi
        System.out.println("Estimasi Enkripsi: " + ((selesaiEn - mulaiEn) / 1000.0) + " detik\n"); //waktu estimasi enkripsi ditampilkan
        
        
        //PROSES DEKRIPSI
        long mulaiDe = System.currentTimeMillis(); //ambil waktu mulai dari dekripsi
        String decrypted = encrypter.decrypt(encrypted); //proses dekripsi dilakukan
        System.out.print("Hasil Dekripsi: ");
        System.out.println(encrypter.decrypt(encrypted)); //cetak hasil dekripsi
        long selesaiDe = System.currentTimeMillis(); //menghitung waktu estimasi dekripsi
        System.out.println("Estimasi Dekripsi: " + ((selesaiDe - mulaiDe) / 1000.0) + " detik"); //waktu estimasi dekrispi ditampilkan
    }
}