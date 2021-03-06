package com.rftdevgroup.transporthub.data.model.transport;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rftdevgroup.transporthub.data.model.auction.Bid;
import com.rftdevgroup.transporthub.data.model.user.Address;
import com.rftdevgroup.transporthub.data.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "owner")
    private User owner;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "cargo")
    private Cargo cargo;

    //possible new class
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "place_of_load")
    private Address placeOfLoad;
    private LocalDate timeOfLoad;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "place_of_unload")
    private Address placeOfUnload;
    private LocalDate timeOfUnload;

    private int startingPrice;

    @OneToMany(mappedBy = "transport")
    @JsonManagedReference
    private List<Bid> bids;

    public int getCurrentPrice() {
        return bids.size() > 0 ? bids.stream().mapToInt(b -> b.getAmount()).min().getAsInt() : startingPrice;
    }
}
