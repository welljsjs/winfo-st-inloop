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

import java.util.Objects;

public abstract class ProjectItem {
  private String name;
  private String details;
  private double rate;

  public ProjectItem(String name, String details, double rate) {
    Objects.requireNonNull(name, "name must be non-null");
    Objects.requireNonNull(details, "details must be non-null");
    if (name.isEmpty() || details.isEmpty())
      throw new IllegalArgumentException("name and details must be non-empty");
    if (rate < 0)
      throw new IllegalArgumentException("rate must be non-negative");

    this.name = name;
    this.details = details;
    this.rate = rate;
  }

  public void setDetails(String newDetails) {
    Objects.requireNonNull(newDetails, "details must be non-null");
    if (newDetails.isEmpty())
      throw new IllegalArgumentException("details must be non-empty");
    this.details = newDetails;
  }

  public long getCostEstimate() {
    return Math.round(getTimeRequired() * rate) + getMaterialCost();
  }

  public abstract double getTimeRequired();

  public abstract long getMaterialCost();
}