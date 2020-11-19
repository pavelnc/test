import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {NodeDt} from './types';

@Injectable({
  providedIn: 'root'
})
export class NodeService {


  constructor(private http: HttpClient) {
  }

  getNodes(nodeId: number): Observable<NodeDt[]> {
    return this.http.get<NodeDt[]>(`http://localhost:8083/?nodeId=${nodeId}`);
  }

  updateNodes(node: NodeDt): Observable<void> {
    return this.http.put<void>(`http://localhost:8083`, node);
  }
}
