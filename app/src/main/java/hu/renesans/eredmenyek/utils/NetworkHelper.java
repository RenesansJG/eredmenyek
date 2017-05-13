package hu.renesans.eredmenyek.utils;

import org.greenrobot.eventbus.EventBus;

import hu.renesans.eredmenyek.exception.InvalidDataException;
import hu.renesans.eredmenyek.exception.NotFoundException;
import hu.renesans.eredmenyek.exception.UnexpectedErrorException;
import retrofit2.Call;
import retrofit2.Response;

public class NetworkHelper {
    public static <T> void executeCall(Call<T> call, BusEvent<T> event, EventBus bus) {
        Response<T> response;

        try {
            response = call.execute();
            checkError(response);
            T result = response.body();
            event.setCode(response.code());
            event.setResult(result);
        } catch (Exception e) {
            event.setThrowable(e);
        }

        bus.post(event);
    }

    private static <T> void checkError(Response<T> response) {
        if (response.code() == 400) {
            throw new InvalidDataException();
        } else if (response.code() == 404) {
            throw new NotFoundException();
        } else if (response.code() != 200) {
            throw new UnexpectedErrorException();
        }
    }
}
