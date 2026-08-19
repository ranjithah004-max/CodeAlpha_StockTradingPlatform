#include <stdio.h>
#include <stdlib.h>

#define FILE_NAME "accounts.dat"

struct Account {
    long long accountNumber;
    char name[100];
    double balance;
};

void createAccount() {
    struct Account account;
    FILE *file = fopen(FILE_NAME, "ab");

    if (file == NULL) {
        printf("Unable to open file.\n");
        return;
    }

    printf("Enter account number: ");
    scanf("%lld", &account.accountNumber);

    printf("Enter account holder name: ");
    scanf(" %[^\n]", account.name);

    account.balance = 0.0;

    fwrite(&account, sizeof(struct Account), 1, file);
    fclose(file);

    printf("Account created successfully.\n");
}

void depositMoney() {
    struct Account account;
    long long accountNumber;
    double amount;
    int found = 0;

    FILE *file = fopen(FILE_NAME, "r+b");

    if (file == NULL) {
        printf("No accounts found.\n");
        return;
    }

    printf("Enter account number: ");
    scanf("%lld", &accountNumber);

    while (fread(&account, sizeof(struct Account), 1, file)) {
        if (account.accountNumber == accountNumber) {
            printf("Enter deposit amount: ");
            scanf("%lf", &amount);

            if (amount <= 0) {
                printf("Amount must be greater than zero.\n");
                fclose(file);
                return;
            }

            account.balance += amount;

            fseek(file, -sizeof(struct Account), SEEK_CUR);
            fwrite(&account, sizeof(struct Account), 1, file);

            printf("Deposit successful.\n");
            printf("Current balance: %.2lf\n", account.balance);

            found = 1;
            break;
        }
    }

    if (!found) {
        printf("Account not found.\n");
    }

    fclose(file);
}

void withdrawMoney() {
    struct Account account;
    long long accountNumber;
    double amount;
    int found = 0;

    FILE *file = fopen(FILE_NAME, "r+b");

    if (file == NULL) {
        printf("No accounts found.\n");
        return;
    }

    printf("Enter account number: ");
    scanf("%lld", &accountNumber);

    while (fread(&account, sizeof(struct Account), 1, file)) {
        if (account.accountNumber == accountNumber) {
            printf("Enter withdrawal amount: ");
            scanf("%lf", &amount);

            if (amount <= 0) {
                printf("Amount must be greater than zero.\n");
                fclose(file);
                return;
            }

            if (amount > account.balance) {
                printf("Insufficient balance.\n");
                fclose(file);
                return;
            }

            account.balance -= amount;

            fseek(file, -sizeof(struct Account), SEEK_CUR);
            fwrite(&account, sizeof(struct Account), 1, file);

            printf("Withdrawal successful.\n");
            printf("Current balance: %.2lf\n", account.balance);

            found = 1;
            break;
        }
    }

    if (!found) {
        printf("Account not found.\n");
    }

    fclose(file);
}

void balanceEnquiry() {
    struct Account account;
    long long accountNumber;
    int found = 0;

    FILE *file = fopen(FILE_NAME, "rb");

    if (file == NULL) {
        printf("No accounts found.\n");
        return;
    }

    printf("Enter account number: ");
    scanf("%lld", &accountNumber);

    while (fread(&account, sizeof(struct Account), 1, file)) {
        if (account.accountNumber == accountNumber) {
            printf("\nAccount Number: %lld\n", account.accountNumber);
            printf("Account Holder: %s\n", account.name);
            printf("Balance: %.2lf\n", account.balance);

            found = 1;
            break;
        }
    }

    if (!found) {
        printf("Account not found.\n");
    }

    fclose(file);
}

int main() {
    int choice;

    do {
        printf("\n===== Bank Account Management System =====\n");
        printf("1. Create Account\n");
        printf("2. Deposit\n");
        printf("3. Withdraw\n");
        printf("4. Balance Enquiry\n");
        printf("5. Exit\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                createAccount();
                break;

            case 2:
                depositMoney();
                break;

            case 3:
                withdrawMoney();
                break;

            case 4:
                balanceEnquiry();
                break;

            case 5:
                printf("Exiting program.\n");
                break;

            default:
                printf("Invalid choice.\n");
        }

    } while (choice != 5);

    return 0;
}