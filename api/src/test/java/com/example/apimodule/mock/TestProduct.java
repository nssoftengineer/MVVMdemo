package com.example.apimodule.mock;

import com.example.apimodule.api.ApiClient;
import com.example.apimodule.api.apiservice.ApiService;
import com.example.apimodule.api.apiservice.LoginService;
import com.example.apimodule.api.product.Data;
import com.example.apimodule.api.product.SampleData;
import com.google.gson.Gson;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
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
        Gson gson = new Gson();
        SampleData sampleData = gson.fromJson("{'id':'1','phone':{'Home':'16','Work':'00111111','Cell-1':'1111111','Cell-2':'2222222'},'country':'Japan','status':1}", SampleData.class);
        Single<SampleData> sampleDataSingle = Single.just(sampleData);
        when(loginService.getLogin()).thenReturn(sampleDataSingle);
        assertEquals(sampleDataSingle, loginService.getLogin());
        verify(loginService).getLogin();

    }

    @Test
    public void TestPassWithParameter() {
        System.out.println("TestPassWithParameter Method");
        Gson gson = new Gson();
        SampleData sampleData = gson.fromJson("{'id':'2','phone':{'Home':'16','Work':'00111111','Cell-1':'1111111','Cell-2':'2222222'},'country':'Japan','status':1}", SampleData.class);
        Single<SampleData> sampleDataSingle = Single.just(sampleData);
        when(loginService.getLoginById(4)).thenReturn(sampleDataSingle);
        assertEquals(sampleDataSingle, loginService.getLoginById(4));
        verify(loginService).getLoginById(4);
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
