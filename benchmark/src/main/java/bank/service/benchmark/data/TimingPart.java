package bank.service.benchmark.data;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

/**
 * The type Timing part.
 *
 * @author Ruslan Potpaov
 */
@Builder
@Data
public class TimingPart {
  private Integer id;
  private  Long time;
  private Throwable exception;
  @Builder.Default
  private boolean failed = false;
  @Builder.Default
  private boolean initial = false;
  @Builder.Default
  private boolean finished = false;
}
