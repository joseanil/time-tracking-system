import { Component, OnInit } from '@angular/core';
import { Institution } from '../model/institution';
import { InstitutionService } from '../service/institution.service';


@Component({
  selector: 'app-institutions',
  templateUrl: './institutions.component.html',
  styleUrls: ['./institutions.component.css']
})
export class InstitutionsComponent implements OnInit {
  institutions: Institution[];
  selectedInstitution: Institution;
  constructor(private institutionService: InstitutionService) {
  }
  ngOnInit() {
    this.getAllInstitutions();
  }

  getAllInstitutions(): void {
    this.institutionService.getAll().subscribe(institutions => this.institutions = institutions);
  }
  onSelect(institution: Institution): void {
    this.selectedInstitution = institution;
  }

  delete(institution: Institution): void {
    this.institutionService.delete(institution).subscribe(() => this.getAllInstitutions());
  }

  clearSelectedInstitution(): void {
    // this.selectedInstitution = {id: null, name: null, description: null, address: null};
    this.selectedInstitution = new Institution();
  }
}
