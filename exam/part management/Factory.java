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

import java.util.Objects;

public class Factory {
  private Purchasing purchasing;
  private ReceivingStock receivingStock;

  public Factory(Purchasing purchasing, ReceivingStock receivingStock) {
    this.purchasing = Objects.requireNonNull(purchasing, "purchasing must be non-null");
    this.receivingStock = Objects.requireNonNull(receivingStock, "receivingStock must be non-null");
  }

  public Purchasing getPurchasing() {
    return purchasing;
  }

  public ReceivingStock getReceivingStock() {
    return receivingStock;
  }

  public static Part createPart(PartType partType, String id, String name) {
    switch (partType) {
      case COMPONENTS:
        return new Components(id, name);
      case SINGLE_COMPONENT:
        return new SingleComponent(id, name);
      case RESOURCE:
        return new Resource(id, name);
      default:
        throw new IllegalArgumentException("Unknown partType value");
    }
  }
}