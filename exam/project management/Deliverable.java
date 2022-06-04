// Copyright 2022 Julius Schmidt
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

import java.time.LocalDate;
import java.util.Objects;

public class Deliverable extends ProjectItem {
  private long materialCost;
  private double productionTime;
  private LocalDate date;

  public Deliverable(String name, String details,
      double rate, long materialCost, double productionTime,
      LocalDate date) {
    super(name, details, rate);
    if (materialCost < 0 || productionTime <= 0)
      throw new IllegalArgumentException("materialCost and productionTime must be positive");
    Objects.requireNonNull(date, "date must be non-null");

    this.materialCost = materialCost;
    this.productionTime = productionTime;
    this.date = date;
  }

  @Override
  public double getTimeRequired() {
    return productionTime;
  }

  @Override
  public long getMaterialCost() {
    return materialCost;
  }

  public LocalDate getDate() {
    return date;
  }

}