import java.util.*;
import java.io.*;

class Books implements Serializable{

int id;
String bname;
int num_page;
String type;
String author;
int price;

  Books(int id, String bname, int num_page, String type, String author, int price){

    this.id = id;
    this.bname = bname;
    this.num_page = num_page;
    this.type = type;
    this.author = author;
    this.price = price;

  }

  public String toString(){

    return id+" | "+bname+" | "+num_page+" | "+type+" | "+author+" | "+price;

  }

}

class Readability {

  public int letter (String txt){
      int letters = 0;
      for (int i = 0; i < txt.length(); i++)
      {
          if ( (txt.charAt(i) >= 'a') &&( txt.charAt(i) <= 'z') || (txt.charAt(i) >= 'A' )&&( txt.charAt(i) <= 'Z'))
          {
              letters++;
          }
      }

      return letters;
  }

  //word calculation function
  public int word(String txt){
    int countWords = txt.split("\\s").length;
    return countWords;

  }
  // sentences calculation function
  public int sent(String txt){
      int sents = 0;
      for (int i = 0; i < txt.length(); i++)
      {
          if (txt.charAt(i) == '!' || txt.charAt(i)== '?' || txt.charAt(i) == '.')
          {
              sents++;
          }
      }
      return sents;
  }

  public void readability(){

      System.out.print("text: ");

      Scanner sc=new Scanner(System.in);

      String txt= sc.nextLine();
      System.out.println("==========================");
      int L = letter(txt);
      int W = word(txt);
      int S = sent(txt);

      System.out.println("Letters: "+L+"\n");
      System.out.println("Words: "+W+"\n");
      System.out.println("Sentences: "+S+"\n");

      double index =  (0.0588 * L / W * 100) - (0.296 * S / W * 100) - 15.8;

        int grade = (int) Math.round (index);
        if (index < 1)
        {
          System.out.println("Before Grade 1");
          System.out.println("==========================");
        }
        else if (index > 16)
        {
          System.out.println("Above Grade 16");
          System.out.println("==========================");
        }
      else
      {
          System.out.println("Grade: " +grade);
          System.out.println("==========================");
      }

  }

}

class Functions {

  File file = new File ("File.txt");
  ArrayList<Books> b1 = new ArrayList<Books>();
  ListIterator li = null;
  Scanner sc = new Scanner(System.in);
  Readability r = new Readability();

  public void Insert() throws Exception{

    System.out.print("Enter Book ID: ");
    int id = sc.nextInt();

    System.out.print("Enter Book Name: ");
    sc.nextLine();
    String bname = sc.nextLine();

    System.out.print("Enter No.Of Pages: ");
    int num_page = sc.nextInt();

    System.out.print("Enter Book type: ");
    sc.nextLine();
    String type = sc.nextLine();

    System.out.print("Enter Author Name: ");
    String author = sc.nextLine();

    System.out.print("Enter Book Price: ");
    int price = sc.nextInt();

    b1.add(new Books(id, bname, num_page, type, author, price));
    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
    oos.writeObject(b1);
    oos.close();

    System.out.println("==========================");

  }

  public void Display() throws Exception{

    li = b1.listIterator();

    if(file.isFile()){

      ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
      b1 = (ArrayList<Books>)ois.readObject();
      ois.close();

      li = b1.listIterator();

      if (li.hasNext()){

        while(li.hasNext()){

          System.out.println(li.next());

        }

      }

      else {

        System.out.println("There Is No Data In File");

      }

    }

    else {

      System.out.println("File Not Exist");

    }

    System.out.println("==========================");

  }

  public void Search() throws Exception{

    if(file.isFile()){

      ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
      b1 = (ArrayList<Books>)ois.readObject();
      ois.close();

      boolean found = false;
      System.out.print("Enter Book ID: ");
      int x = sc.nextInt();
      System.out.println("==========================");

      li = b1.listIterator();

        while(li.hasNext()){

          Books b = (Books)li.next();

          if (b.id == x){

            System.out.println(b);
            System.out.println("==========================");
            found = true;
            break;

          }

      }

      if (found == false){

        System.out.println("The ID Not found");
        System.out.println("==========================");

      }

    }

    else {

      System.out.println("File Not Exist");
      System.out.println("==========================");

    }

  }

  public void update() throws Exception{

    if(file.isFile()){

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        b1 = (ArrayList<Books>)ois.readObject();
        ois.close();

        li = b1.listIterator();

        boolean found = false;
        System.out.print("Enter ID Of Book: ");
        int x = sc.nextInt();
        System.out.println("==========================");

        while(li.hasNext()){

          Books b = (Books)li.next();

          if (b.id == x){

            System.out.print("Enter Book ID: ");
            int id = sc.nextInt();

            System.out.print("Enter Book Name: ");
            sc.nextLine();
            String bname = sc.nextLine();

            System.out.print("Enter No.Of Pages: ");
            int num_page = sc.nextInt();

            System.out.print("Enter Book type: ");
            sc.nextLine();
            String type = sc.nextLine();

            System.out.print("Enter Author Name: ");
            String author = sc.nextLine();

            System.out.print("Enter Book Price: ");
            int price = sc.nextInt();

            li.set(new Books(id,bname,num_page,type,author,price));
            System.out.println("==========================");
            found = true;
            break;

          }

        }

      if (found){

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(b1);
        oos.close();
        System.out.println("Data Updated Successfully ");
        System.out.println("==========================");

      }

      else {

        System.out.println("The ID Not found");
        System.out.println("==========================");

      }

    }

    else {

      System.out.println("File Not Exist");
      System.out.println("==========================");

    }

  }

  public void delete() throws Exception {

    if (file.isFile()) {

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        b1 = (ArrayList<Books>)ois.readObject();
        ois.close();

        li = b1.listIterator();

        boolean found = false;
        System.out.print("Enter Book ID: ");
        int x = sc.nextInt();
        System.out.println("==========================");

        while (li.hasNext()) {

            Books b = (Books)li.next();

            if (b.id == x) {

                li.remove();
                found = true;
                break;

            }

        }

        if (found){

          ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
          oos.writeObject(b1);
          oos.close();
          System.out.println("Data Deleted Successfully");
          System.out.println("==========================");

        }

        else {

            System.out.println("The ID Not found");
            System.out.println("==========================");

        }

    }

    else {

        System.out.println("File Not Exist");
        System.out.println("==========================");

    }

  }

  public void Choice() throws Exception{

    System.out.println("==========================");

    while(true){

    System.out.println("[1] Insert The Book");
    System.out.println("[2] Display All Books");
    System.out.println("[3] Search About Book");
    System.out.println("[4] Readability");
    System.out.println("[5] Update The Book");
    System.out.println("[6] Delete The Book");
    System.out.println("[7] Exit");
    System.out.println("==========================");
    System.out.print("Enter Your Choice: ");
    int choice = sc.nextInt();
    System.out.println("==========================");

    switch(choice){

      case 1: Insert();
      break;

      case 2: Display();
      break;

      case 3: Search();
      break;

      case 4: r.readability();
      break;

      case 5: update();
      break;

      case 6: delete();
      break;

      case 7: System.exit(0);
      break;

      default:
      System.out.println("Pls Enter From 1 To 7 ");
      System.out.println("==========================");
      break;

    }

  }

  }

}

public class BookStore {
public static void main(String[] args) throws Exception {

  Functions f = new Functions();
  f.Choice();

}

}
