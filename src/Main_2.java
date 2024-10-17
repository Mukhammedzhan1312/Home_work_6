import java.util.ArrayList;
import java.util.List;

interface IReportBuilder {
    void setHeader(String header);
    void setContent(String content);
    void setFooter(String footer);
    void addSection(String sectionName, String sectionContent);
    Report getReport();
}

class TextReportBuilder implements IReportBuilder {
    private Report report = new Report();

    @Override
    public void setHeader(String header) {
        report.setHeader("Text Report Header: " + header);
    }

    @Override
    public void setContent(String content) {
        report.setContent("Content: " + content);
    }

    @Override
    public void setFooter(String footer) {
        report.setFooter("Text Report Footer: " + footer);
    }

    @Override
    public void addSection(String sectionName, String sectionContent) {
        report.addSection("Section: " + sectionName + "\n" + sectionContent);
    }

    @Override
    public Report getReport() {
        return report;
    }
}

class HtmlReportBuilder implements IReportBuilder {
    private Report report = new Report();

    @Override
    public void setHeader(String header) {
        report.setHeader("<h1>" + header + "</h1>");
    }

    @Override
    public void setContent(String content) {
        report.setContent("<p>" + content + "</p>");
    }

    @Override
    public void setFooter(String footer) {
        report.setFooter("<footer>" + footer + "</footer>");
    }

    @Override
    public void addSection(String sectionName, String sectionContent) {
        report.addSection("<h2>" + sectionName + "</h2><p>" + sectionContent + "</p>");
    }

    @Override
    public Report getReport() {
        return report;
    }
}


class Report {
    private String header;
    private String content;
    private String footer;
    private List<String> sections = new ArrayList<>();

    public void setHeader(String header) {
        this.header = header;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public void addSection(String section) {
        sections.add(section);
    }

    public void display() {
        System.out.println(header);
        System.out.println(content);
        for (String section : sections) {
            System.out.println(section);
        }
        System.out.println(footer);
    }
}

enum ReportType {
    TEXT,
    HTML,
    XML,
    PDF
}

enum ReportStyleType {
    DEFAULT,
    CUSTOM
}

class ReportDirector {
    public void constructReport(IReportBuilder builder) {
        builder.setHeader("Annual Report");
        builder.setContent("This is the content of the report.");
        builder.addSection("Introduction", "This section introduces the report.");
        builder.addSection("Data", "This section contains data.");
        builder.setFooter("End of Report");
    }

    public IReportBuilder getBuilder(ReportType type) {
        switch (type) {
            case TEXT:
                return new TextReportBuilder();
            case HTML:
                return new HtmlReportBuilder();
            default:
                throw new IllegalArgumentException("Unsupported report type");
        }
    }
}

public class Main_2 {
    public static void main(String[] args) {
        ReportDirector director = new ReportDirector();

        IReportBuilder textBuilder = director.getBuilder(ReportType.TEXT);
        director.constructReport(textBuilder);
        Report textReport = textBuilder.getReport();
        System.out.println("Text Report:");
        textReport.display();

        IReportBuilder htmlBuilder = director.getBuilder(ReportType.HTML);
        director.constructReport(htmlBuilder);
        Report htmlReport = htmlBuilder.getReport();
        System.out.println("\nHTML Report:");
        htmlReport.display();
    }
}

