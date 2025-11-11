package Assignment8.com.report.elements;

import Assignment8.com.report.visitor.ReportVisitor;

//ReportElement interface - represents an element in the report system.

public interface ReportElement {
    void accept(ReportVisitor visitor);
}
