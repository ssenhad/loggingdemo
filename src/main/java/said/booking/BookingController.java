package said.booking;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/bookings")
@Api(
        name = "Hotel Booking API",
        description = "Provides a list of methods that manage hotel bookings",
        stage = ApiStage.RC)
public class BookingController {

/*    private List<HotelBooking> bookings;

    public BookingController() {

            bookings = new ArrayList<>();
            bookings.add(new HotelBooking("Marriot", 200.50, 3));
            bookings.add(new HotelBooking("Ibis", 90, 4));
            bookings.add(new HotelBooking("Novotel", 140.74, 1));

        //    bookingRepository.save(bookings);
        }*/

    private BookingRepository bookingRepository;

    @Autowired
    public BookingController(BookingRepository bookingRepository){
        this.bookingRepository = bookingRepository;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ApiMethod(description = "Get all hotel bookings from the database")
    public List<HotelBooking> getAll(){
        return bookingRepository.findAll();
       // return bookings;
    }

    @RequestMapping(value = "/affordable/{price}", method = RequestMethod.GET)
    @ApiMethod(description = "Get all hotel bookings where the price per night is less than the provided value")
    public List<HotelBooking> getAffordable(@ApiPathParam(name = "price") @PathVariable double price){
       return bookingRepository.findByPricePerNightLessThan(price);
      /*  return bookings.stream().filter(x->x.getPricePerNight()<=price)
                        .collect(Collectors.toList());*/
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ApiMethod(description = "Create a hotel booking and save it to the database")
    public List<HotelBooking> create(@RequestBody HotelBooking hotelBooking){
        bookingRepository.save(hotelBooking);

        return bookingRepository.findAll();
      /*  bookings.add(hotelBooking);
        return bookings;*/
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ApiMethod(description = "Remove the hotel booking with the provided ID from the database")
    public List<HotelBooking> remove(@ApiPathParam(name = "id") @PathVariable long id){
        bookingRepository.delete(id);

        return bookingRepository.findAll();
/*        bookings.remove(id);
        return bookings;*/
    }
}
