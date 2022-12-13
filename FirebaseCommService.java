//
//
// TOP Development
// FirebaseCommService.java
//
//

package com.topdev.jarvised.Services;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.topdev.jarvised.Callbacks.FirebaseCommCallback;
import com.topdev.jarvised.CustomObjects.JarvisAdmin;
import com.topdev.jarvised.CustomObjects.JarvisClass;
import com.topdev.jarvised.CustomObjects.JarvisComment;
import com.topdev.jarvised.CustomObjects.JarvisParent;
import com.topdev.jarvised.CustomObjects.JarvisPointSystemSettings;
import com.topdev.jarvised.CustomObjects.JarvisReport;
import com.topdev.jarvised.CustomObjects.JarvisSchoolYearDetails;
import com.topdev.jarvised.CustomObjects.JarvisStudent;
import com.topdev.jarvised.CustomObjects.JarvisTeacher;
import com.topdev.jarvised.CustomObjects.JarvisUser;
import com.topdev.jarvised.Enums.AcademicYearType;
import com.topdev.jarvised.Enums.ReportType;
import com.topdev.jarvised.MainActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseCommService {

    /*
     *
     * Member Variables
     *
     */

    private FirebaseCommCallback mCallback;



    /*
     *
     * Database Identifiers
     *
     */

    private static String USERS_IDENTIFIER = "Users";
    private static String STUDENTS_IDENTIFIER = "Students";
    private static String TEACHERS_IDENTIFIER = "Teachers";
    private static String ADMINS_IDENTIFIER = "Admins";
    private static String REPORTS_IDENTIFIER = "Reports";
    private static String SCHOOLS_IDENTIFIER = "Schools";
    private static String CLASSES_IDENTIFIER = "Classes";
    private static String REPORT_COMMENTS_IDENTIFIER = "Comments";
    private static String SETTINGS_IDENTIFIER = "Settings";
    private static String POINT_SYSTEM_SETTINGS_ID = "PointSystem";
    private static String SCHOOL_YEAR_DETAILS_ID = "SchoolYear";



    /*
     *
     * Database Field Identifiers
     *
     */

    private static String BIRTHDAY_ID = "birthday";
    private static String CONTACT_EMAIL_ID = "contactEmail";
    private static String CONTACT_NAME_ID = "contactFullName";
    private static String FULL_NAME_ID = "fullName";
    private static String PARENT_USER_KEY_ID = "parentUserKey";
    private static String SCHOOL_NAME_ID = "schoolName";
    private static String USER_TYPE_ID = "userType";
    private static String HAS_CONDITION_ID = "hasCondition";
    private static String STUDENTS_ID = "students";
    private static String TEACHERS_ID = "teachers";
    private static String SUBJECT_ID = "subject";
    private static String FLAG_TYPE_ID = "flagType";
    private static String DATE_ID = "date";
    private static String TIME_ID = "time";
    private static String DESCRIPTION_ID = "description";
    private static String STATUS_ID = "status";
    private static String REPORTED_BY_ID = "reportedBy";
    private static String WAS_PRESENT_ID = "wasPresent";
    private static String CLASS_TITLE_ID = "title";
    private static String TEACHER_NAME_ID = "teacher";
    private static String STUDENT_NAMES_ID = "students";
    private static String CLASSES_ID = "classes";
    private static String REPORT_TYPE_ID = "reportType";
    private static String REPORT_DETAILS_ID = "reportDetails";
    private static String STUDENT_ID = "student";
    private static String AUTHOR_ID = "author";
    private static String COMMENT_DETAILS_ID = "commentDetails";
    private static String ATTENDANCE_REPORT_DEDUCTION_ID = "attendanceReportDeduction";
    private static String CONFLICT_REPORT_DEDUCTION_ID = "conflictReportDeduction";
    private static String NEGATIVE_BEHAVIOR_DEDUCTION_ID = "negativeBehaviorDeduction";
    private static String SECLUDED_DEDUCTION_ID = "secludedReportDeduction";
    private static String POINTS_PER_CLASS_ID = "pointsPerClass";
    private static String ACADEMIC_YEAR_TYPE_ID = "academicYearType";
    private static String YEAR_START_DATE_ID = "startDate";
    private static String TERM_ONE_END_DATE_ID = "termOneEndDate";
    private static String TERM_TWO_END_DATE_ID = "termTwoEndDate";
    private static String TERM_THREE_END_DATE_ID = "termThreeEndDate";
    private static String TERM_FOUR_END_DATE_ID = "termFourEndDate";



    /*
     *
     * Local Enum
     *
     */

    public enum FirebaseMethod {

        LOGIN {
            @Override
            public String toString() {
                return "Login";
            }
        },
        DELETE_CLASS {
            @Override
            public String toString() {
                return "Delete Class";
            }
        },
        SIGNUP {
            @Override
            public String toString() {
                return "Signup";
            }
        },
        GET_USER_INFO {
            @Override
            public String toString() {
                return "Get User Info";
            }
        },
        GET_ALL_SCHOOLS {
            @Override
            public String toString() {
                return "Get All Schools";
            }
        },
        GET_ALL_TEACHERS {
            @Override
            public String toString() {
                return "Get All Teachers";
            }
        },
        GET_ALL_STUDENTS {
            @Override
            public String toString() {
                return "Get All Students";
            }
        },
        GET_ALL_ADMINS {
            @NonNull
            @Override
            public String toString() {
                return "Get All Admins";
            }
        },
        GET_ALL_REPORTS {
            @NonNull
            @Override
            public String toString() {
                return "Get All Reports";
            }
        },
        ADD_NEW_SCHOOL {
            @Override
            public String toString() {
                return "Add New School";
            }
        },
        ADD_NEW_STUDENT {
            @Override
            public String toString() {
                return "Add New Student";
            }
        },
        CREATE_ACCOUNT {
            @Override
            public String toString() {
                return "Create Account";
            }
        },
        GET_USER_REPORTS {
            @Override
            public String toString() {
                return "Get User Reports";
            }
        },
        ADD_NEW_TEACHER {
            @Override
            public String toString() {
                return "Add New Teacher";
            }
        },
        GET_ALL_CLASSES {
            @Override
            public String toString() {
                return "Get All Classes";
            }
        },
        ADD_NEW_CLASS {
            @Override
            public String toString() {
                return "Add New Class";
            }
        },
        GET_TEACHERS_STUDENTS {
            @NonNull
            @Override
            public String toString() {
                return "Get Teachers Students";
            }
        },
        ADD_NEW_REPORT {
            @NonNull
            @Override
            public String toString() {
                return "Add New Report";
            }
        },
        GET_REPORT_COMMENTS {
            @NonNull
            @Override
            public String toString() {
                return "Get Report Comments";
            }
        },
        ADD_REPORT_COMMENT {
            @NonNull
            @Override
            public String toString() {
                return "Add Report Comment";
            }
        },
        GET_POINT_SYSTEM_SETTINGS {
            @NonNull
            @Override
            public String toString() {
                return "Get Point System Settings";
            }
        },
        UPDATE_POINT_SYSTEM_SETTINGS {
            @NonNull
            @Override
            public String toString() {
                return "Update Point System Settings";
            }
        },
        GET_SCHOOL_YEAR_DETAILS {
            @NonNull
            @Override
            public String toString() {
                return "Get School Year Details";
            }
        },
        UPDATE_SCHOOL_YEAR_DETAILS {
            @NonNull
            @Override
            public String toString() {
                return "Update School Year Details";
            }
        }

    }



    /*
     *
     * Constructor
     *
     */

    public FirebaseCommService(FirebaseCommCallback callback) {

        mCallback = callback;

    }



    /*
     *
     * Productive Methods
     *
     */

    public void attemptLogin(final String email, final String password) {

        Log.i(MainActivity.LOG_TAG, "Login Attempt | Email: " + email + " | Password: " + password);
        new Thread(new Runnable() {
            @Override
            public void run() {

                FirebaseAuth auth = FirebaseAuth.getInstance();
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            Log.i(MainActivity.LOG_TAG, "Login Attempt Successful");
                            getUserInfo(email);

                        } else {

                            Log.i(MainActivity.LOG_TAG, "Login Attempt Failed");
                            mCallback.errorResponse(FirebaseMethod.LOGIN);

                        }

                    }
                });

            }
        }).start();

    }

    public void deleteClass(final String email, final String className) {

        Log.i(MainActivity.LOG_TAG, "Delete Class: " + className + " started!");
        new Thread(new Runnable() {
            @Override
            public void run() {

                FirebaseFirestore database = FirebaseFirestore.getInstance();
//                FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
//                        .setTimestampsInSnapshotsEnabled(true)
//                        .build();
//                database.setFirestoreSettings(settings);

                database.collection(USERS_IDENTIFIER).document(email).collection(CLASSES_IDENTIFIER).document(className).delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()) {

                                    mCallback.successResponse(FirebaseMethod.DELETE_CLASS);

                                } else {

                                    mCallback.errorResponse(FirebaseMethod.DELETE_CLASS);

                                }

                            }
                        });

            }
        }).start();

    }



    /*
     *
     * Getter Methods
     *
     */

    public void getUserInfo(final String email) {

        Log.i(MainActivity.LOG_TAG, "Get User Info Attempt | Email: " + email);
        new Thread(new Runnable() {
            @Override
            public void run() {

                FirebaseFirestore database = FirebaseFirestore.getInstance();
//                FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
//                        .setTimestampsInSnapshotsEnabled(true)
//                        .build();
//                database.setFirestoreSettings(settings);

                database.collection(USERS_IDENTIFIER).document(email).get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                                if (task.isSuccessful()) {

                                    Log.i(MainActivity.LOG_TAG, "Get User Info Successful");
                                    DocumentSnapshot snapshot = task.getResult();
                                    String fullname = snapshot.getString(FULL_NAME_ID);
                                    String userType = snapshot.getString(USER_TYPE_ID);
                                    String contactEmail = snapshot.getString(CONTACT_EMAIL_ID);

                                    Log.i(MainActivity.LOG_TAG, "User Type: " + userType);

                                    JarvisUser user = null;
                                    if (userType.matches(JarvisUser.UserType.STUDENT.toString())) {

                                        String birthday = snapshot.getString(BIRTHDAY_ID);
                                        user = new JarvisStudent(fullname, contactEmail);

                                        Log.i(MainActivity.LOG_TAG, "Student Data Retrieved | fullName: " + fullname
                                                + " | birthday: " + birthday + " | email: " + email);

                                    } else if (userType.matches(JarvisUser.UserType.ADMINISTRATOR.toString())) {

                                        List<String> teachers;
                                        if (snapshot.contains(TEACHERS_ID)) {
                                            teachers = (List<String>) snapshot.get(TEACHERS_ID);
                                        } else {
                                            teachers = new ArrayList<>();
                                        }

                                        List<String> students;
                                        if (snapshot.contains(STUDENTS_ID)) {
                                            students = (List<String>) snapshot.get(STUDENTS_ID);
                                        } else {
                                            students = new ArrayList<>();
                                        }

                                        user = new JarvisAdmin(fullname, contactEmail, teachers, students);

                                        Log.i(MainActivity.LOG_TAG, "Administrator Data Retrieved | fullName: " + fullname
                                                + " | email: " + contactEmail);

                                    } else if (userType.matches(JarvisUser.UserType.PARENT.toString())) {

                                        List<String> students;
                                        if (snapshot.contains(STUDENTS_ID)) {
                                            students = (List<String>) snapshot.get(STUDENTS_ID);
                                        } else {
                                            students = new ArrayList<>();
                                        }
                                        user = new JarvisParent(fullname, contactEmail, students);

                                        Log.i(MainActivity.LOG_TAG, "Parent Data Retrieved | fullName: " + fullname
                                                + " | email: " + contactEmail);

                                    } else if (userType.matches(JarvisUser.UserType.TEACHER.toString())) {

                                        String subject = snapshot.getString(SUBJECT_ID);
                                        String birthday = snapshot.getString(BIRTHDAY_ID);
                                        List<String> students;
                                        if (snapshot.contains(STUDENTS_ID)) {
                                            students = (List<String>) snapshot.get(STUDENTS_ID);
                                        } else {
                                            students = new ArrayList<>();
                                        }

                                        List<String> classes;
                                        if (snapshot.contains(CLASSES_ID)) {
                                            classes = (List<String>) snapshot.get(CLASSES_ID);
                                        } else {
                                            classes = new ArrayList<>();
                                        }

                                        user = new JarvisTeacher(fullname, contactEmail, classes, students);

                                        Log.i(MainActivity.LOG_TAG, "Teacher Data Retrieved | fullName: " + fullname
                                                + " | birthday: " + birthday + " | email: " + contactEmail + " | subject: " + subject);

                                    }

                                    if (user != null) {

                                        Log.i(MainActivity.LOG_TAG, "user != null | Sending Success Response...");
                                        mCallback.successResponseSingle(FirebaseMethod.GET_USER_INFO, user);

                                    } else {

                                        Log.i(MainActivity.LOG_TAG, "user == null | Sending Failure Response...");
                                        mCallback.errorResponse(FirebaseMethod.GET_USER_INFO);

                                    }

                                } else {

                                    Log.i(MainActivity.LOG_TAG, "Get User Info Failed");
                                    mCallback.errorResponse(FirebaseMethod.GET_USER_INFO);

                                }

                            }

                        });

            }

        }).start();

    }

    public void getAllTeachers(final String email) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                FirebaseFirestore database = FirebaseFirestore.getInstance();
//                FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
//                        .setTimestampsInSnapshotsEnabled(true)
//                        .build();
//                database.setFirestoreSettings(settings);

                database.collection(USERS_IDENTIFIER).document(email).collection(TEACHERS_IDENTIFIER).get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                                if (task.isSuccessful() && task.getResult() != null) {

                                    for (DocumentSnapshot snapshot : task.getResult().getDocuments()) {

                                        String fullname = snapshot.getString(FULL_NAME_ID);
                                        JarvisTeacher teacher = new JarvisTeacher(fullname);
                                        mCallback.successResponseSingle(FirebaseMethod.GET_ALL_TEACHERS, teacher);

                                    }

                                }

                            }
                        });

                mCallback.successResponse(FirebaseMethod.GET_ALL_TEACHERS);

            }
        }).start();

    }

    public void getAllStudents(final String email) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                FirebaseFirestore database = FirebaseFirestore.getInstance();
//                FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
//                        .setTimestampsInSnapshotsEnabled(true)
//                        .build();
//                database.setFirestoreSettings(settings);

                database.collection(USERS_IDENTIFIER).document(email).collection(STUDENTS_IDENTIFIER).get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful() && task.getResult() != null) {

                            for (DocumentSnapshot snapshot : task.getResult().getDocuments()) {

                                String fullname = snapshot.getString(FULL_NAME_ID);
                                JarvisStudent student = new JarvisStudent(fullname, email);
                                mCallback.successResponseSingle(FirebaseMethod.GET_ALL_STUDENTS, student);

                            }

                        } else {

                            Log.i(MainActivity.LOG_TAG, "Task is Unsuccessful");
                            mCallback.errorResponse(FirebaseMethod.GET_ALL_STUDENTS);

                        }

                    }
                });

                mCallback.successResponse(FirebaseMethod.GET_ALL_STUDENTS);

            }
        }).start();

    }

    public void getAllAdmins(final String email) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                FirebaseFirestore database = FirebaseFirestore.getInstance();
                database.collection(USERS_IDENTIFIER).document(email).collection(ADMINS_IDENTIFIER).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful() && task.getResult() != null) {

                            for (DocumentSnapshot snapshot : task.getResult().getDocuments()) {

                                String fullname = snapshot.getString(FULL_NAME_ID);
                                JarvisAdmin admin = new JarvisAdmin(fullname);
                                mCallback.successResponseSingle(FirebaseMethod.GET_ALL_ADMINS, admin);

                            }

                        } else {

                            mCallback.errorResponse(FirebaseMethod.GET_ALL_ADMINS);

                        }

                    }
                });

            }
        }).start();

    }

    public void getUserReports(final String email, final String username) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                final FirebaseFirestore database = FirebaseFirestore.getInstance();
//                FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
//                        .setTimestampsInSnapshotsEnabled(true)
//                        .build();
//                database.setFirestoreSettings(settings);

                database.collection(USERS_IDENTIFIER).document(email).collection(REPORTS_IDENTIFIER).get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                                if (task.isSuccessful()) {

                                    if (task.getResult() != null) {

                                        ArrayList<JarvisReport> reports = new ArrayList<>();
                                        for (int i = 0; i < task.getResult().getDocuments().size(); i++) {

                                            DocumentSnapshot snapshot = task.getResult().getDocuments().get(i);
                                            String student;
                                            if (snapshot.contains(STUDENT_ID)) {
                                                student = snapshot.getString(STUDENT_ID);
                                            } else {
                                                student = "";
                                            }

                                            String reportedBy = "";
                                            if (snapshot.contains(REPORTED_BY_ID)) {
                                                reportedBy = snapshot.getString(REPORTED_BY_ID);
                                            }

                                            if (student != null && (username.matches(student) || username.matches(reportedBy))) {

                                                String id = snapshot.getId();
                                                String flagType = "";
                                                String description = "";
                                                String date = "";
                                                ReportType type = null;
                                                if (snapshot.contains(FLAG_TYPE_ID)) flagType = snapshot.getString(FLAG_TYPE_ID);

                                                if (snapshot.contains(DESCRIPTION_ID)) {
                                                    description = snapshot.getString(DESCRIPTION_ID);
                                                }
                                                if (snapshot.contains(DATE_ID)) {
                                                    date = snapshot.getString(DATE_ID);
                                                }

                                                if (flagType != null) {
                                                    if (flagType.matches(ReportType.ATTENDENCE.toString())) {
                                                        type = ReportType.ATTENDENCE;
                                                    } else if (flagType.matches(ReportType.CONFLICT.toString())) {
                                                        type = ReportType.CONFLICT;
                                                    } else if (flagType.matches(ReportType.EXPELLED.toString())) {
                                                        type = ReportType.EXPELLED;
                                                    } else if (flagType.matches(ReportType.SECLUDED.toString())) {
                                                        type = ReportType.SECLUDED;
                                                    } else if (flagType.matches(ReportType.SIP.toString())) {
                                                        type = ReportType.SIP;
                                                    }
                                                } else {
                                                    type = ReportType.ALL;
                                                }

                                                JarvisReport report = new JarvisReport(id, type, description, date, reportedBy, student);
                                                reports.add(report);

                                            }

                                        }

                                        mCallback.successResponseList(FirebaseMethod.GET_USER_REPORTS, reports);

                                    }

                                } else {

                                    mCallback.errorResponse(FirebaseMethod.GET_USER_REPORTS);

                                }

                            }
                        });

            }
        }).start();

    }

    public void getAllClasses(final String email) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                FirebaseFirestore database = FirebaseFirestore.getInstance();
//                FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
//                        .setTimestampsInSnapshotsEnabled(true)
//                        .build();
//                database.setFirestoreSettings(settings);

                database.collection(USERS_IDENTIFIER).document(email).collection(CLASSES_IDENTIFIER).get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                                if (task.isSuccessful()) {

                                    if (task.getResult() != null) {

                                        for (int i = 0; i < task.getResult().getDocuments().size(); i++) {

                                            DocumentSnapshot snapshot = task.getResult().getDocuments().get(i);
                                            String classTitle = "";
                                            String teacherName = "";
                                            List<String> studentNames;

                                            if (snapshot.contains(CLASS_TITLE_ID)) {
                                                classTitle = snapshot.getString(CLASS_TITLE_ID);
                                            }

                                            if (snapshot.contains(TEACHER_NAME_ID)) {
                                                teacherName = snapshot.getString(TEACHER_NAME_ID);
                                            }

                                            if (snapshot.contains(STUDENT_NAMES_ID)) {
                                                studentNames = (List<String>) snapshot.get(STUDENT_NAMES_ID);
                                            } else {
                                                studentNames = new ArrayList<>();
                                            }

                                            JarvisClass jClass = new JarvisClass(classTitle, teacherName, studentNames);

                                            mCallback.successResponseSingle(FirebaseMethod.GET_ALL_CLASSES, jClass);

                                        }

                                        mCallback.successResponse(FirebaseMethod.GET_ALL_CLASSES);

                                    }

                                } else {

                                    mCallback.errorResponse(FirebaseMethod.GET_ALL_CLASSES);

                                }

                            }
                        });

            }
        }).start();

    }

    public void getTeachersStudents(final String email, final JarvisTeacher teacher) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                FirebaseFirestore database = FirebaseFirestore.getInstance();

                for (int i = 0; i < teacher.getClasses().size()-1; i++) {

                    database.collection(USERS_IDENTIFIER).document(email).collection(CLASSES_IDENTIFIER)
                            .document(teacher.getClasses().get(i)).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                            if (task.isSuccessful()) {

                                DocumentSnapshot snapshot = task.getResult();
                                List<String> students = (List<String>) snapshot.get(STUDENTS_ID);
                                mCallback.successResponseList(FirebaseMethod.GET_TEACHERS_STUDENTS, ((ArrayList<String>) students));

                            } else {

                                mCallback.errorResponse(FirebaseMethod.GET_TEACHERS_STUDENTS);

                            }

                        }
                    });

                }

            }
        }).start();

    }

    public void getAllReports(final String email) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                FirebaseFirestore database = FirebaseFirestore.getInstance();
                database.collection(USERS_IDENTIFIER).document(email).collection(REPORTS_IDENTIFIER).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful() && task.getResult() != null) {

                            List<DocumentSnapshot> snapshot = task.getResult().getDocuments();
                            for (int i = 0; i < snapshot.size()-1; i++) {

                                String id = snapshot.get(i).getId();
                                String date = snapshot.get(i).getString(DATE_ID);
                                String reportDetails = snapshot.get(i).getString(REPORT_DETAILS_ID);
                                String type = snapshot.get(i).getString(REPORT_TYPE_ID);
                                String reportedBy = snapshot.get(i).getString(REPORTED_BY_ID);
                                String reportedStudent = snapshot.get(i).getString(STUDENT_ID);
                                ReportType reportType;
                                if (type.matches(ReportType.ATTENDENCE.toString())) {
                                    reportType = ReportType.ATTENDENCE;
                                } else if (type.matches(ReportType.BEHAVIOR.toString())) {
                                    reportType = ReportType.BEHAVIOR;
                                } else if (type.matches(ReportType.CONFLICT.toString())) {
                                    reportType = ReportType.CONFLICT;
                                } else if (type.matches(ReportType.EXPELLED.toString())) {
                                    reportType = ReportType.EXPELLED;
                                } else if (type.matches(ReportType.MOOD.toString())) {
                                    reportType = ReportType.MOOD;
                                } else if (type.matches(ReportType.SECLUDED.toString())) {
                                    reportType = ReportType.SECLUDED;
                                } else if (type.matches(ReportType.SIP.toString())) {
                                    reportType = ReportType.SIP;
                                } else {
                                    reportType = ReportType.ALL;
                                }

                                JarvisReport report = new JarvisReport(id, reportType, reportDetails, date, reportedBy, reportedStudent);
                                mCallback.successResponseSingle(FirebaseMethod.GET_ALL_REPORTS, report);

                            }

                        } else {

                            mCallback.errorResponse(FirebaseMethod.GET_ALL_REPORTS);

                        }

                    }
                });

            }
        }).start();

    }

    public void getReportComments(final String email, final JarvisReport selectedReport) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                FirebaseFirestore database = FirebaseFirestore.getInstance();
                database.collection(USERS_IDENTIFIER).document(email).collection(REPORTS_IDENTIFIER)
                        .document(selectedReport.getID()).collection(REPORT_COMMENTS_IDENTIFIER).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {

                            if (task.getResult() != null) {

                                ArrayList<JarvisComment> comments = new ArrayList<>();
                                List<DocumentSnapshot> snapshots = task.getResult().getDocuments();
                                for (int i = 0; i < snapshots.size()-1; i++) {

                                    String date = snapshots.get(i).getString(DATE_ID);
                                    String author = snapshots.get(i).getString(AUTHOR_ID);
                                    String commentDetails = snapshots.get(i).getString(COMMENT_DETAILS_ID);
                                    JarvisComment comment = new JarvisComment(date, commentDetails, author);
                                    comments.add(comment);

                                }
                                mCallback.successResponseList(FirebaseMethod.GET_REPORT_COMMENTS, comments);

                            } else {

                                mCallback.successResponse(FirebaseMethod.GET_REPORT_COMMENTS);

                            }

                        } else {

                            mCallback.errorResponse(FirebaseMethod.GET_REPORT_COMMENTS);

                        }

                    }
                });

            }
        }).start();

    }

    public void getPointSystemSettings(final String email) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                FirebaseFirestore database = FirebaseFirestore.getInstance();
                database.collection(USERS_IDENTIFIER).document(email).collection(SETTINGS_IDENTIFIER)
                        .document(POINT_SYSTEM_SETTINGS_ID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        if (task.isSuccessful()) {

                            if (task.getResult() != null) {

                                DocumentSnapshot snapshot = task.getResult();
                                Long attendanceDeduction = (Long) snapshot.get(ATTENDANCE_REPORT_DEDUCTION_ID);
                                Long pointsPerClass = (Long) snapshot.get(POINTS_PER_CLASS_ID);
                                Long conflictDeduction = (Long) snapshot.get(CONFLICT_REPORT_DEDUCTION_ID);
                                Long negativeBehaviorDeduction = (Long) snapshot.get(NEGATIVE_BEHAVIOR_DEDUCTION_ID);
                                Long secludedDeduction = (Long) snapshot.get(SECLUDED_DEDUCTION_ID);
                                JarvisPointSystemSettings settings = new JarvisPointSystemSettings(pointsPerClass.intValue(),
                                        attendanceDeduction.intValue(), conflictDeduction.intValue(),
                                        negativeBehaviorDeduction.intValue(), secludedDeduction.intValue());

                                mCallback.successResponseSingle(FirebaseMethod.GET_POINT_SYSTEM_SETTINGS, settings);

                            }

                        } else {

                            mCallback.errorResponse(FirebaseMethod.GET_POINT_SYSTEM_SETTINGS);

                        }

                    }
                });

            }
        }).start();

    }

    public void getSchoolYearDetails(final String email) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                FirebaseFirestore database = FirebaseFirestore.getInstance();
                database.collection(USERS_IDENTIFIER).document(email).collection(SETTINGS_IDENTIFIER)
                        .document(SCHOOL_YEAR_DETAILS_ID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        if (task.isSuccessful()) {

                            if (task.getResult() != null) {

                                DocumentSnapshot snapshot = task.getResult();
                                String yearType = snapshot.getString(ACADEMIC_YEAR_TYPE_ID);
                                String startDate = snapshot.getString(YEAR_START_DATE_ID);
                                String termOneEndDate = snapshot.getString(TERM_ONE_END_DATE_ID);
                                String termTwoEndDate = snapshot.getString(TERM_TWO_END_DATE_ID);
                                String termThreeEndDate = snapshot.getString(TERM_THREE_END_DATE_ID);
                                String termFourendDate = snapshot.getString(TERM_FOUR_END_DATE_ID);
                                JarvisSchoolYearDetails details = null;
                                if (yearType.matches(AcademicYearType.SEMESTER.toString())) {

                                    details = new JarvisSchoolYearDetails(AcademicYearType.SEMESTER, startDate,
                                            termOneEndDate, termTwoEndDate, termThreeEndDate, termFourendDate);

                                } else if (yearType.matches(AcademicYearType.TRIMESTER.toString())) {

                                    details = new JarvisSchoolYearDetails(AcademicYearType.TRIMESTER, startDate,
                                            termOneEndDate, termTwoEndDate, termThreeEndDate, termFourendDate);

                                }

                                if (details == null) {

                                    mCallback.errorResponse(FirebaseMethod.GET_SCHOOL_YEAR_DETAILS);

                                } else {

                                    mCallback.successResponseSingle(FirebaseMethod.GET_SCHOOL_YEAR_DETAILS, details);

                                }

                            }

                        } else {

                            mCallback.errorResponse(FirebaseMethod.GET_SCHOOL_YEAR_DETAILS);

                        }

                    }
                });

            }
        }).start();

    }



    /*
     *
     * Setter Methods
     *
     */

    public void createAccount(final String email, final String password, final String role,
                              final String schoolName, final String fullname) {

        final FirebaseAuth auth = FirebaseAuth.getInstance();

        new Thread(new Runnable() {
            @Override
            public void run() {

                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {

                                    mCallback.successResponse(FirebaseMethod.CREATE_ACCOUNT);

                                } else {

                                    mCallback.errorResponse(FirebaseMethod.CREATE_ACCOUNT);
                                    if (task.getException() != null) {
                                        Log.i(MainActivity.LOG_TAG, task.getException().getLocalizedMessage());
                                    }

                                }

                            }
                        });

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                Map<String, Object> newUserData = new HashMap<>();
                newUserData.put(USER_TYPE_ID, role);
                newUserData.put(FULL_NAME_ID, fullname);
                newUserData.put(SCHOOL_NAME_ID, schoolName);

                FirebaseFirestore database = FirebaseFirestore.getInstance();
//                FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
//                        .setTimestampsInSnapshotsEnabled(true)
//                        .build();
//                database.setFirestoreSettings(settings);

                database.collection(USERS_IDENTIFIER).document(email).set(newUserData)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()) {

                                    mCallback.successResponse(FirebaseMethod.CREATE_ACCOUNT);

                                } else {

                                    mCallback.errorResponse(FirebaseMethod.CREATE_ACCOUNT);

                                }


                            }
                        });

            }
        }).start();

    }

    public void addNewStudent(final String currentUserEmail, final String studentFullName) {

        Log.i(MainActivity.LOG_TAG, "Add New Student Attempt | Student Full Name: " + studentFullName);

        new Thread(new Runnable() {
            @Override
            public void run() {

                Map<String, Object> newStudentData = new HashMap<>();
                newStudentData.put(FULL_NAME_ID, studentFullName);
                newStudentData.put(USER_TYPE_ID, "Student");

                FirebaseFirestore database = FirebaseFirestore.getInstance();
//                FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
//                        .setTimestampsInSnapshotsEnabled(true)
//                        .build();
//                database.setFirestoreSettings(settings);

                database.collection(USERS_IDENTIFIER).document(currentUserEmail).collection(STUDENTS_IDENTIFIER).add(newStudentData)
                        .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {

                        if (task.isSuccessful()) {

                            Log.i(MainActivity.LOG_TAG, "Add New Student Successful");
                            mCallback.successResponse(FirebaseMethod.ADD_NEW_STUDENT);

                        } else {

                            Log.i(MainActivity.LOG_TAG, "Add New Student Failed");
                            mCallback.errorResponse(FirebaseMethod.ADD_NEW_STUDENT);

                        }

                    }
                });

            }
        }).start();

    }

    public void addNewTeacher(final String currentUserEmail, final List<String> currentTeachersList, final String teacherFullName, final String contactEmail, final String subject) {

        Log.i(MainActivity.LOG_TAG, "Add New Teacher Attempt | Teacher Full Name: " + teacherFullName +
                " | Contact Email: " + contactEmail + " | Subject: " + subject);

        new Thread(new Runnable() {
            @Override
            public void run() {

                Map<String, Object> newTeacherData = new HashMap<>();
                newTeacherData.put(FULL_NAME_ID, teacherFullName);
                newTeacherData.put(USER_TYPE_ID, "Teacher");

                FirebaseFirestore database = FirebaseFirestore.getInstance();
//                FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
//                        .setTimestampsInSnapshotsEnabled(true)
//                        .build();
//                database.setFirestoreSettings(settings);

                database.collection(USERS_IDENTIFIER).document(contactEmail).set(newTeacherData)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()) {

                                    Log.i(MainActivity.LOG_TAG, "Add New Student Successful");
                                    mCallback.successResponse(FirebaseMethod.ADD_NEW_TEACHER);

                                } else {

                                    Log.i(MainActivity.LOG_TAG, "Add New Student Failed");
                                    mCallback.errorResponse(FirebaseMethod.ADD_NEW_TEACHER);

                                }

                            }
                        });

                Map<String, Object> updateUserData = new HashMap<>();
                currentTeachersList.add(contactEmail);
                updateUserData.put(STUDENTS_ID, currentTeachersList);

                database.collection(USERS_IDENTIFIER).document(currentUserEmail).set(updateUserData, SetOptions.merge())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()) {

                                    mCallback.successResponse(FirebaseMethod.ADD_NEW_TEACHER);

                                } else {

                                    mCallback.errorResponse(FirebaseMethod.ADD_NEW_TEACHER);

                                }

                            }
                        });

            }
        }).start();

    }

    public void addNewClass(final String email, final String className) {

        Log.i(MainActivity.LOG_TAG, "Add New Class Attempt | Class Name: " + className);
        new Thread(new Runnable() {
            @Override
            public void run() {

                Map<String, Object> newClassData = new HashMap<>();
                newClassData.put(CLASS_TITLE_ID, className);

                FirebaseFirestore database = FirebaseFirestore.getInstance();
//                FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
//                        .setTimestampsInSnapshotsEnabled(true)
//                        .build();
//                database.setFirestoreSettings(settings);

                database.collection(USERS_IDENTIFIER).document(email).collection(CLASSES_IDENTIFIER).document(className).set(newClassData)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()) {

                                    mCallback.successResponse(FirebaseMethod.ADD_NEW_CLASS);

                                } else {

                                    mCallback.errorResponse(FirebaseMethod.ADD_NEW_CLASS);

                                }

                            }
                        });

            }
        }).start();

    }

    public void addNewReport(ReportType type, String reportDetails, String reportedStudent, JarvisUser reportedBy) {

        Calendar cal = Calendar.getInstance();

        String amOrPm = "";
        if (cal.get(Calendar.AM_PM) == Calendar.PM)
            amOrPm = "PM"; else amOrPm = "AM";

        String month = "";
        month = String.valueOf(cal.get(Calendar.MONTH) + 1);
        if (month.length() == 0) month = "0" + month;

        String dayOfMonth = "";
        dayOfMonth = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
        if (dayOfMonth.length() == 0) dayOfMonth = "0" + dayOfMonth;

        String documentId = (cal.get(Calendar.YEAR) + "_" + month + "_" + dayOfMonth + "_"
                + cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE) + "_" + amOrPm + "_"
                + reportedStudent.replace(" ", "_") + "_" + type.toString());

        String date = "";
        if (cal.get(Calendar.MONTH) == Calendar.JANUARY) date = "January";
        else if (cal.get(Calendar.MONTH) == Calendar.FEBRUARY) date = "February";
        else if (cal.get(Calendar.MONTH) == Calendar.MARCH) date = "March";
        else if (cal.get(Calendar.MONTH) == Calendar.APRIL) date = "April";
        else if (cal.get(Calendar.MONTH) == Calendar.MAY) date = "May";
        else if (cal.get(Calendar.MONTH) == Calendar.JUNE) date = "June";
        else if (cal.get(Calendar.MONTH) == Calendar.JULY) date = "July";
        else if (cal.get(Calendar.MONTH) == Calendar.AUGUST) date = "August";
        else if (cal.get(Calendar.MONTH) == Calendar.SEPTEMBER) date = "September";
        else if (cal.get(Calendar.MONTH) == Calendar.OCTOBER) date = "October";
        else if (cal.get(Calendar.MONTH) == Calendar.NOVEMBER) date = "November";
        else if (cal.get(Calendar.MONTH) == Calendar.DECEMBER) date = "December";
        date += (" " + cal.get(Calendar.DAY_OF_MONTH) + ", " + cal.get(Calendar.YEAR) + " "
                + cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE) + " " + cal.get(Calendar.AM_PM));

        Map<String, Object> newReportData = new HashMap<>();
        newReportData.put(DATE_ID, date);
        newReportData.put(REPORT_TYPE_ID, type.toString());
        newReportData.put(REPORT_DETAILS_ID, reportDetails);
        newReportData.put(REPORTED_BY_ID, reportedBy.getFullName());
        newReportData.put(STUDENT_ID, reportedStudent);

        Log.i(MainActivity.LOG_TAG, "Add New Report Attempted | Report Type: " + type.toString()
                + " | Date: " + date + " | Report Details: " + reportDetails + " | Reported Student: "
                + reportedStudent + " | Reported By: " + reportedBy.getFullName());

        new Thread(new Runnable() {
            @Override
            public void run() {

                FirebaseFirestore database = FirebaseFirestore.getInstance();
                database.collection(USERS_IDENTIFIER).document(reportedBy.getContactEmail()).collection(REPORTS_IDENTIFIER)
                        .document(documentId).set(newReportData).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {

                            mCallback.successResponse(FirebaseMethod.ADD_NEW_REPORT);

                        } else {

                            mCallback.errorResponse(FirebaseMethod.ADD_NEW_REPORT);

                        }

                    }
                });

            }
        }).start();

    }

    public void addNewComment(final String email, final JarvisReport report, final JarvisComment newComment) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                Map<String, Object> newCommentData = new HashMap<>();
                newCommentData.put(DATE_ID, newComment.getPostDate());
                newCommentData.put(AUTHOR_ID, newComment.getAuthor());
                newCommentData.put(COMMENT_DETAILS_ID, newComment.getComment());

                FirebaseFirestore database = FirebaseFirestore.getInstance();
                database.collection(USERS_IDENTIFIER).document(email).collection(REPORTS_IDENTIFIER)
                        .document(report.getID()).collection(REPORT_COMMENTS_IDENTIFIER).add(newCommentData)
                        .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {

                        if (task.isSuccessful()) {

                            mCallback.successResponse(FirebaseMethod.ADD_REPORT_COMMENT);

                        } else {

                            mCallback.errorResponse(FirebaseMethod.ADD_REPORT_COMMENT);

                        }

                    }
                });

            }
        }).start();

    }

    public void updatePointSystemSettings(final String email, JarvisPointSystemSettings settings) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                Map<String, Object> updatedSettings = new HashMap<>();
                updatedSettings.put(POINTS_PER_CLASS_ID, settings.getPointsPerClass());
                updatedSettings.put(ATTENDANCE_REPORT_DEDUCTION_ID, settings.getAttendanceReportDeduction());
                updatedSettings.put(CONFLICT_REPORT_DEDUCTION_ID, settings.getConflictReportDeduction());
                updatedSettings.put(NEGATIVE_BEHAVIOR_DEDUCTION_ID, settings.getNegativeBehaviorDeduction());
                updatedSettings.put(SECLUDED_DEDUCTION_ID, settings.getSecludedDeduction());

                FirebaseFirestore database = FirebaseFirestore.getInstance();
                database.collection(USERS_IDENTIFIER).document(email).collection(SETTINGS_IDENTIFIER)
                        .document(POINT_SYSTEM_SETTINGS_ID).set(updatedSettings)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {

                            mCallback.successResponse(FirebaseMethod.UPDATE_POINT_SYSTEM_SETTINGS);

                        } else {

                            mCallback.errorResponse(FirebaseMethod.UPDATE_POINT_SYSTEM_SETTINGS);

                        }

                    }
                });

            }
        }).start();

    }

    public void updateSchoolYearDetails(final String email, JarvisSchoolYearDetails details) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                Map<String, Object> updatedDetails = new HashMap<>();
                updatedDetails.put(ACADEMIC_YEAR_TYPE_ID, details.getAcademicYearType().toString());
                updatedDetails.put(YEAR_START_DATE_ID, details.getStartDate());
                updatedDetails.put(TERM_ONE_END_DATE_ID, details.getTermOneEndDate());
                updatedDetails.put(TERM_TWO_END_DATE_ID, details.getTermTwoEndDate());
                updatedDetails.put(TERM_THREE_END_DATE_ID, details.getTermThreeEndDate());
                updatedDetails.put(TERM_FOUR_END_DATE_ID, details.getTermFourEndDate());

                FirebaseFirestore database = FirebaseFirestore.getInstance();
                database.collection(USERS_IDENTIFIER).document(email).collection(SETTINGS_IDENTIFIER)
                        .document(SCHOOL_YEAR_DETAILS_ID).set(updatedDetails)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()) {

                                    mCallback.successResponse(FirebaseMethod.UPDATE_SCHOOL_YEAR_DETAILS);

                                } else {

                                    mCallback.errorResponse(FirebaseMethod.UPDATE_SCHOOL_YEAR_DETAILS);

                                }

                            }
                        });

            }
        }).start();

    }

}
