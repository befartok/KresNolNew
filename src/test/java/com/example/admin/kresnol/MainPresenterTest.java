package com.example.admin.kresnol;

import android.content.res.Resources;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * Created by admin on 15.05.19.
 */
public class MainPresenterTest {


    private MainPresenter presenter;

    @Mock
    MainPresenter mockedPresenter;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        MainActivity mockView = mock(MainActivity.class);
        Resources easy = mock(Resources.class);
        when(mockView.getResources()).thenReturn(easy);
        presenter = new MainPresenter(mockView);

    }

    @Test
    public void testIncrementTotalWin() throws Exception {

        assertEquals(5, presenter.incrementTotalWin(4));

    }


}