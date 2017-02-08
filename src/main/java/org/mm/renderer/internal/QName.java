package org.mm.renderer.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nonnull;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * @author Josef Hardi <josef.hardi@stanford.edu> <br>
 *         Stanford Center for Biomedical Informatics Research
 */
public class QName implements Value<String> {

   private final String prefixedName;

   public QName(@Nonnull String prefixedName) {
      this.prefixedName = checkNotNull(prefixedName);
   }

   @Override
   public String getActualObject() {
      return prefixedName;
   }

   @Override
   public boolean equals(Object o) {
      if (o == null) {
         return false;
      }
      if (this == o) {
         return true;
      }
      if (!(o instanceof QName)) {
         return false;
      }
      QName other = (QName) o;
      return Objects.equal(prefixedName, other.getActualObject());
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(prefixedName);
   }

   @Override
   public String toString() {
      return MoreObjects.toStringHelper(this)
            .addValue(prefixedName)
            .toString();
   }
}