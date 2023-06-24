import java.util.Scanner;

class Visitor {
    private String name;
    private String contactNumber;
    private String email;

    public Visitor() {
    }

    public Visitor(String name, String contactNumber, String email) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.email = email;
    }

    // Getter and Setter methods

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

class Appointment {
    private String date;
    private String timeSlot;
    private String doctorName;
    private Visitor visitor;

    public Appointment() {
    }

    public Appointment(String date, String timeSlot, String doctorName, Visitor visitor) {
        this.date = date;
        this.timeSlot = timeSlot;
        this.doctorName = doctorName;
        this.visitor = visitor;
    }

    // Getter and Setter methods

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
}

class Doctor {
    private String name;
    private String specialization;

    public Doctor() {
    }

    public Doctor(String name, String specialization) {
        this.name = name;
        this.specialization = specialization;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}

class Clinic {
    private String clinicName;
    private String location;
    private Appointment[] appointments;
    private Doctor[] doctors;
    private int appointmentCount;
    private int doctorCount;

    public Clinic(String clinicName, String location, int maxAppointments, int maxDoctors) {
        this.clinicName = clinicName;
        this.location = location;
        appointments = new Appointment[maxAppointments];
        doctors = new Doctor[maxDoctors];
        appointmentCount = 0;
        doctorCount = 0;
    }

    public void displayClinicInformation() {
        System.out.println("Clinic Name: " + clinicName);
        System.out.println("Location: " + location);
        System.out.println("Maximum Appointments: " + appointments.length);
        System.out.println("Maximum Doctors: " + doctors.length);
        System.out.println();
    }

    public void displayVisitorsList() {
        if (appointmentCount == 0) {
            System.out.println("No visitors found.");
            return;
        }

        System.out.println(clinicName);
        System.out.println("Location: " + location);
        System.out.println("Visitors List:");

        for (int i = 0; i < appointmentCount; i++) {
            Appointment appointment = appointments[i];
            Visitor visitor = appointment.getVisitor();
            System.out.println("Visitor Name: " + visitor.getName());
            System.out.println("Contact Number: " + visitor.getContactNumber());
            System.out.println("Email: " + visitor.getEmail());
            System.out.println();
        }
    }

    public void addVisitor() {
        if (appointmentCount >= appointments.length) {
            System.out.println("Appointment slots are full. Cannot add a new visitor.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        Appointment appointment = new Appointment();

        System.out.print("Enter visitor name: ");
        String name = scanner.nextLine();
        System.out.print("Enter contact number: ");
        String contactNumber = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        Visitor visitor = new Visitor(name, contactNumber, email);
        appointment.setVisitor(visitor);

        System.out.print("Enter appointment date (DD/MM/YYYY): ");
        appointment.setDate(scanner.nextLine());
        System.out.print("Enter appointment time slot: ");
        appointment.setTimeSlot(scanner.nextLine());

        appointments[appointmentCount] = appointment;
        appointmentCount++;

        System.out.println("Visitor and appointment added successfully.");
    }

    public void editVisitorDetails() {
        if (appointmentCount == 0) {
            System.out.println("No visitors found.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter visitor name to edit details: ");
        String visitorName = scanner.nextLine();

        for (int i = 0; i < appointmentCount; i++) {
            Appointment appointment = appointments[i];
            Visitor visitor = appointment.getVisitor();
            if (visitor.getName().equalsIgnoreCase(visitorName)) {
                System.out.println("Visitor details found. Enter new details:");

                System.out.print("Enter visitor name: ");
                visitor.setName(scanner.nextLine());
                System.out.print("Enter contact number: ");
                visitor.setContactNumber(scanner.nextLine());
                System.out.print("Enter email: ");
                visitor.setEmail(scanner.nextLine());

                System.out.println("Visitor details updated successfully.");
                return;
            }
        }

        System.out.println("Visitor not found.");
    }

    public void viewAppointmentScheduleForDoctor(String date, String doctorName) {
        System.out.println("Appointment Schedule for Doctor: " + doctorName + " on " + date);

        boolean found = false;

        for (int i = 0; i < appointmentCount; i++) {
            Appointment appointment = appointments[i];
            if (appointment.getDate().equals(date) && appointment.getDoctorName().equalsIgnoreCase(doctorName)) {
                Visitor visitor = appointment.getVisitor();
                System.out.println("Time Slot: " + appointment.getTimeSlot());
                System.out.println("Visitor Name: " + visitor.getName());
                System.out.println("Contact Number: " + visitor.getContactNumber());
                System.out.println("Email: " + visitor.getEmail());
                System.out.println();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No appointments found for the given date and doctor.");
        }
    }

    public void viewConsolidatedAppointmentSchedule(String date) {
        System.out.println("Consolidated Appointment Schedule for " + date);

        boolean found = false;

        for (int i = 0; i < appointmentCount; i++) {
            Appointment appointment = appointments[i];
            if (appointment.getDate().equals(date)) {
                Visitor visitor = appointment.getVisitor();
                System.out.println("Doctor Name: " + appointment.getDoctorName());
                System.out.println("Time Slot: " + appointment.getTimeSlot());
                System.out.println("Visitor Name: " + visitor.getName());
                System.out.println("Contact Number: " + visitor.getContactNumber());
                System.out.println("Email: " + visitor.getEmail());
                System.out.println();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No appointments found for the given date.");
        }
    }

    public void bookAppointment(String doctorName, String date, String timeSlot, Visitor visitor) {
        if (appointmentCount >= appointments.length) {
            System.out.println("Appointment slots are full. Cannot book a new appointment.");
            return;
        }

        Appointment appointment = new Appointment(date, timeSlot, doctorName, visitor);
        appointments[appointmentCount] = appointment;
        appointmentCount++;

        System.out.println("Appointment booked successfully.");
    }

    public void editAppointment(String visitorName, String date, String timeSlot) {
        if (appointmentCount == 0) {
            System.out.println("No appointments found.");
            return;
        }

        for (int i = 0; i < appointmentCount; i++) {
            Appointment appointment = appointments[i];
            Visitor visitor = appointment.getVisitor();
            if (visitor.getName().equalsIgnoreCase(visitorName) && appointment.getDate().equals(date) && appointment.getTimeSlot().equalsIgnoreCase(timeSlot)) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Appointment details found. Enter new details:");

                System.out.print("Enter doctor name: ");
                appointment.setDoctorName(scanner.nextLine());
                System.out.print("Enter appointment date (DD/MM/YYYY): ");
                appointment.setDate(scanner.nextLine());
                System.out.print("Enter appointment time slot: ");
                appointment.setTimeSlot(scanner.nextLine());

                System.out.println("Appointment details updated successfully.");
                return;
            }
        }

        System.out.println("Appointment not found.");
    }

    public void editDoctorDetails(String doctorName, String specialization) {
        if (doctorCount == 0) {
            System.out.println("No doctors found.");
            return;
        }

        for (int i = 0; i < doctorCount; i++) {
            Doctor doctor = doctors[i];
            if (doctor.getName().equalsIgnoreCase(doctorName)) {
                System.out.println("Doctor details found. Enter new details:");

                Scanner scanner = new Scanner(System.in);

                System.out.print("Enter doctor name: ");
                doctor.setName(scanner.nextLine());
                System.out.print("Enter doctor specialization: ");
                doctor.setSpecialization(scanner.nextLine());

                System.out.println("Doctor details updated successfully.");
                return;
            }
        }

        System.out.println("Doctor not found.");
    }
}

public class ClinicAppointmentManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Clinic clinic = new Clinic("Aditya's Clinic", "Mumbai", 100, 10);

        while (true) {
            System.out.println("Welcome to the Appointment Management System");
            System.out.println("1. Visitors List");
            System.out.println("2. Add new Visitor");
            System.out.println("3. Edit Visitor Details");
            System.out.println("4. View Appointment Schedule for a Day (Individual Doctor)");
            System.out.println("5. View Consolidated Appointment Schedule for a Day");
            System.out.println("6. Book an Appointment");
            System.out.println("7. Edit/Cancel Appointment");
            System.out.println("8. Add/Edit Doctor Details");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            String visitorName;
            switch (choice) {
                case 1:
                    clinic.displayVisitorsList();
                    break;
                case 2:
                    clinic.addVisitor();
                    break;
                case 3:
                    clinic.editVisitorDetails();
                    break;
                case 4:
                    System.out.print("Enter date (DD/MM/YYYY): ");
                    String date = scanner.nextLine();
                    System.out.print("Enter doctor name: ");
                    String doctorName = scanner.nextLine();
                    clinic.viewAppointmentScheduleForDoctor(date, doctorName);
                    break;
                case 5:
                    System.out.print("Enter date (DD/MM/YYYY): ");
                    date = scanner.nextLine();
                    clinic.viewConsolidatedAppointmentSchedule(date);
                    break;
                case 6:
                    System.out.print("Enter doctor name: ");
                    doctorName = scanner.nextLine();
                    System.out.print("Enter appointment date (DD/MM/YYYY): ");
                    date = scanner.nextLine();
                    System.out.print("Enter appointment time slot: ");
                    String timeSlot = scanner.nextLine();
                    System.out.print("Enter visitor name: ");
                    visitorName = scanner.nextLine();
                    Visitor visitor = new Visitor(visitorName, "", "");
                    clinic.bookAppointment(doctorName, date, timeSlot, visitor);
                    break;
                case 7:
                    System.out.print("Enter visitor name: ");
                    visitorName = scanner.nextLine();
                    System.out.print("Enter appointment date (DD/MM/YYYY): ");
                    date = scanner.nextLine();
                    System.out.print("Enter appointment time slot: ");
                    timeSlot = scanner.nextLine();
                    clinic.editAppointment(visitorName, date, timeSlot);
                    break;
                case 8:
                    System.out.print("Enter doctor name: ");
                    doctorName = scanner.nextLine();
                    System.out.print("Enter doctor specialization: ");
                    String specialization = scanner.nextLine();
                    clinic.editDoctorDetails(doctorName, specialization);
                    break;
                case 9:
                    System.out.println("Thank you for using the Appointment Management System.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            System.out.println();
        }
    }
}
