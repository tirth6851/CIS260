import javax.swing.*;

public class gui {
    public static void main(String[] args) {
        String name = JOptionPane.showInputDialog("Enter your name");
        JOptionPane.showMessageDialog(null, "Hello " + name + "!");

        // Get age as String, then convert to int
        String ageStr = JOptionPane.showInputDialog("How old are you?");
        int age = Integer.parseInt(ageStr); // Convert String to int
        JOptionPane.showMessageDialog(null, "You are " + age + " years old!");

        // Confirm age with Yes/No dialog
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you are " + age + " years old?");
        if (confirm == JOptionPane.YES_OPTION && age>=18){
            JOptionPane.showMessageDialog(null, "You are good to enter!");
        }
        else{
            if(age<18){JOptionPane.showMessageDialog(null, "You are not good to enter!");}
            else if (age>18) {
                JOptionPane.showMessageDialog(null, "Are you sure you wana go cause you can enter?");
                int senConfirm = JOptionPane.showConfirmDialog(null, "You dont want to procide?");
                if (senConfirm == JOptionPane.YES_OPTION){JOptionPane.showMessageDialog(null, "YAY!, welcome");}
                else{JOptionPane.showMessageDialog(null, "have a good one and hope to see you again!");}
            }

        }

    }
}