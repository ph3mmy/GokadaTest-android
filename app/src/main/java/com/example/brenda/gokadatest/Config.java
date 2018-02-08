package com.example.brenda.gokadatest;

/**
 * Created by Belal on 10/24/2015.
 */
public class Config {

    //Address of our scripts of the CRUD
    public static final String URL_GET_EMP2 = "http://www.intellimobilesystems.com/PHP_Scripts/getEmp2.php?uid=";
    public static final String URL_ADD="http://www.intellimobilesystems.com/PHP_Scripts/addEmp.php";
    public static final String URL_ADD1="http://www.intellimobilesystems.com/PHP_Scripts/addEmp1.php";
    public static final String URL_ADD2="http://www.intellimobilesystems.com/PHP_Scripts/addEmp1.php";
    public static final String URL_GET_ALL = "http://www.intellimobilesystems.com/PHP_Scripts/getAllEmp.php?name=";
    public static final String URL_GET_ALL2 = "http://www.intellimobilesystems.com/PHP_Scripts/getAllEmp2.php?uid=";
    public static final String URL_GET_ALL3 = "http://www.intellimobilesystems.com/PHP_Scripts/getAllEmp3.php?uid=";
    public static final String URL_GET_ALL4 = "http://www.intellimobilesystems.com/PHP_Scripts/getAllEmp4.php?email=";
    public static final String URL_GET_EMP = "http://www.intellimobilesystems.com/PHP_Scripts/getEmp.php?uid=";
    public static final String URL_UPDATE_EMP = "http://www.intellimobilesystems.com/PHP_Scripts/updateEmp.php";
    public static final String URL_UPDATE_EMP2 = "http://www.intellimobilesystems.com/PHP_Scripts/updateEmp2.php";
    public static final String URL_DELETE_EMP = "http://www.intellimobilesystems.com/PHP_Scripts/deleteEmp.php?uid=";

    //Keys that will be used to send the request to php scripts''''''''''''''''''''
    public static final String KEY_EMP_ID = "uid";
    public static final String KEY_EMP_NAME = "name";
    public static final String KEY_EMP_DESG = "desg";
    public static final String KEY_EMP_EMAIL = "email";
    public static final String KEY_EMP_SAL = "salary";
    public static final String KEY_EMP_DATE = "date";
    public static final String KEY_EMP_SUBJECT = "subject";
    public static final String KEY_EMP_CLASS = "class1";
    public static final String KEY_EMP_GRADE = "grade";
    public static final String KEY_EMP_EXAM = "exam";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "uid";
    public static final String TAG_NAME = "name";
    public static final String TAG_CLASS = "class1";
    public static final String TAG_DESG = "desg";
    public static final String TAG_SAL = "salary";
    public static final String TAG_DATE = "date";
    public static final String TAG_SUBJECT = "subject";
    public static final String TAG_CLASS1 = "class2";
    public static final String TAG_GRADE = "grade";
    public static final String TAG_EXAM = "exam";
    //employee id to pass with intent
    public static final String EMP_ID = "emp_id";
}
