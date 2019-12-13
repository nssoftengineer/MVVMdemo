package com.example.apimodule.mock;

import com.example.apimodule.api.ApiClient;
import com.example.apimodule.api.apiservice.ApiService;
import com.example.apimodule.api.apiservice.LoginService;
import com.example.apimodule.api.product.Data;
import com.example.apimodule.api.product.SampleData;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import io.reactivex.Single;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TestProduct {

    private static final String TAG = "TestStudent";


    private LoginService loginService = Mockito.mock(LoginService.class);
    ApiService apiService;
    private final String BASE_URL ="https://api.myjson.com/bins/";


    @BeforeClass
    public static void setUpClass() {
        System.out.println("setUpClass: ");

    }

    @Before
    public void setUp() {
        System.out.println("setUp:.........................................................");
        ApiClient.retrofitInit(BASE_URL,true);
    }

    @Test
    public void TestPass() {
        System.out.println("TestPass Method");
        String s = "{'id':'Me','phone':{'Home':'16','Work':'00111111','Cell-1':'1111111','Cell-2':'2222222'},'country':'Japan','status':1}";

        SampleData sampleData=new SampleData();
        sampleData.setCountry("India");
        when(loginService.getLogin()).thenReturn(sampleData);
        assertEquals(sampleData, loginService.getLogin());
        verify(loginService).getLogin();
    }


    @After
    public void tearDown() {
        System.out.println("tearDown: ");
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("tearDownClass: ");
    }
}
