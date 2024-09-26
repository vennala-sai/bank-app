export interface Account {
    accountId?: number; 
    balance: number;
    type: string;
    interestRate?: number;
    nextCheckNumber?: number;
}