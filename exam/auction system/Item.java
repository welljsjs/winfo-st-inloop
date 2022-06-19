// Copyright 2022 Julius Schmidt
// 
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
// 
//     http://www.apache.org/licenses/LICENSE-2.0
// 
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Item {
  private String name;
  private String description;
  private long minPrice;
  private List<Bid> allBids = new ArrayList<Bid>();
  private Bid highestBid;

  public Item(String name, String description, long minPrice) {
    Objects.requireNonNull(name, "name must be non-null");
    Objects.requireNonNull(description, "description must be non-null");
    if (name.isEmpty() || description.isEmpty())
      throw new IllegalArgumentException("name and description must be non-empty");
    if (minPrice <= 0)
      throw new IllegalArgumentException("minPrice must be positive");

    this.name = name;
    this.description = description;
    this.minPrice = minPrice;
  }

  public void addBid(Person bidder, long price) {
    Objects.requireNonNull(bidder, "bidder must not be null");
    if (price <= 0)
      throw new IllegalArgumentException("price must be positive");

    if (price < minPrice || (highestBid != null && price <= highestBid.getPrice()))
      return;

    Bid bid = new Bid(bidder, price);

    if (highestBid == null || bid.getPrice() > highestBid.getPrice()) // NOTE: highestBid may be null at this point!
      highestBid = bid;

    allBids.add(bid);
  }

  public List<Bid> getAllBids() {
    return allBids;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public Bid getHighestBid() {
    return highestBid;
  }

  @Override
  public String toString() {
    return name + ": " + description + " (minimum bidding price: " + minPrice + " EUR)";
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Item))
      throw new IllegalArgumentException("obj must be an instance of Item to compare");
    return ((Item) obj).getName().equals(this.getName());
  }

  @Override
  public int hashCode() {
    return name.hashCode();
  }
}
