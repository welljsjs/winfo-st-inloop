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

public class Paint extends Material {
  private final static double limit = 0.02;
  private int numberOfCoats;
  private double squareMetersPerLiter;

  public Paint(String name, double price, int numberOfCoats, double squareMetersPerLiter) {
    super(name, price);
    if (numberOfCoats <= 0 || squareMetersPerLiter <= 0)
      throw new IllegalArgumentException("numberOfCoats and squareMetersPerLiter must be positive");
    this.numberOfCoats = numberOfCoats;
    this.squareMetersPerLiter = squareMetersPerLiter;
  }

  public int getNumberOfCoats() {
    return numberOfCoats;
  }

  public double getSquareMetersPerLiter() {
    return squareMetersPerLiter;
  }

  @Override
  public int getMaterialRequirements(Surface surface) {
    Objects.requireNonNull(surface, "surface must be non-null");
    final double litersPerBucket = 0.5;
    final int bucketsNeeded = (int) (Math
        .floor((numberOfCoats * surface.getArea() / squareMetersPerLiter - limit) / litersPerBucket)) + 1;
    return bucketsNeeded;
  }

}