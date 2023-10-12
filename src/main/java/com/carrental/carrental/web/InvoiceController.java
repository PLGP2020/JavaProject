package com.carrental.carrental.web;

import com.carrental.carrental.domain.Invoice;
import com.carrental.carrental.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/invoices")
    public String listInvoices(Model model) {
        model.addAttribute("invoices", invoiceService.getAllInvoices());
        return "invoices/invoices";
    }

    @GetMapping("invoices/new")
    public String createInvoiceForm(Model model) {
        Invoice invoice = new Invoice();
        model.addAttribute("invoice", invoice);
        return "invoices/create_invoice";
    }

    @PostMapping("/invoices")
    public String saveInvoice(@ModelAttribute("invoice") Invoice invoice) {
        invoiceService.saveInvoice(invoice);
        return "redirect:/invoices";
    }

    @GetMapping("/invoices/edit/{id}")
    public String editInvoiceForm(@PathVariable Long id, Model model) {
        model.addAttribute("invoice", invoiceService.getInvoiceById(id));
        return "invoices/edit_invoice";
    }

    @PostMapping("/invoices/{id}")
    public String updateInvoice(@PathVariable Long id,
                                @ModelAttribute("invoice") Invoice invoice,
                                Model model) {
        Invoice existingInvoice = invoiceService.getInvoiceById(id);
        existingInvoice.setInvoiceID(invoice.getInvoiceID());
        existingInvoice.setDateOfIssue(invoice.getDateOfIssue());
        existingInvoice.setDateOfPayment(invoice.getDateOfPayment());
        existingInvoice.setTotalAmount(invoice.getTotalAmount());
        invoiceService.updateInvoice(existingInvoice);
        return "redirect:/invoices";
    }

    @GetMapping("invoices/delete/{id}")
    public String deleteInvoice(@PathVariable Long id) {
        invoiceService.deleteInvoiceById(id);
        return "redirect:/invoices";
    }

}
