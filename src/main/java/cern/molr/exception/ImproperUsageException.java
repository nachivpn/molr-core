/*
 * © Copyright 2016 CERN. This software is distributed under the terms of the Apache License Version 2.0, copied
 * verbatim in the file “COPYING“. In applying this licence, CERN does not waive the privileges and immunities granted
 * to it by virtue of its status as an Intergovernmental Organization or submit itself to any jurisdiction.
 */

package cern.molr.exception;

/**
 * Exception to be used whenever its not possible to
 * {@link cern.molr.commons.mole.Mole#discover(Class)} methods in a {@link cern.molr.commons.domain.Mission}
 *
 * @author nachivpn
 */
public class ImproperUsageException extends Exception {

    public ImproperUsageException(String message) {
        super(message);
    }

    public ImproperUsageException(Throwable cause) {
        super(cause);
    }

    public ImproperUsageException(String message, Throwable cause) {
        super(message, cause);
    }
}
